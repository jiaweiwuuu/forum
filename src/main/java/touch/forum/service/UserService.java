package touch.forum.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import touch.forum.entity.*;
import touch.forum.exception.IncorrectPasswordException;
import touch.forum.exception.UserNotExistException;
import touch.forum.exception.UsernameExistException;
import touch.forum.mapper.UserMapper;
import touch.forum.utils.HashUtil;
import touch.forum.utils.TicketUtil;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    UserMapper userMapper;

    @Autowired
    HostHolder hostHolder;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender mailSender;

    static private String VERIFICATION_CODE= "Verification code";

    private static String DEFAULT_IMAGE_URL = "9f065c20-6c44-45c7-bd1a-472c5df3bb8d.jpeg";

    public static String DEFAULT_HEAD_URL = "www.baidu.com";
    public String createUser(String username, String password) throws UsernameExistException {
        User user = userMapper.getUserByName(username);
        if(user != null){
            throw new UsernameExistException("user name exist");
        }
        user = new User();
        user.setName(username).setSalt(UUID.randomUUID().toString().substring(0,5)).setHeadUrl(DEFAULT_HEAD_URL).setFirstLogin(1);
        user.setPassword(HashUtil.MD5(password+user.getSalt()));

        userMapper.addUser(user);
        int id = userMapper.getUserByName(username).getId();
        user.setId(id);
        User u = new User().setHeadUrl(DEFAULT_IMAGE_URL).setId(id);
        userMapper.updateUser(u);
        String token = TicketUtil.setTicket(user,redisTemplate,false);
        return token;
    }

    public TokenAndLogin login(String username, String password, boolean rememberMe) throws UserNotExistException, IncorrectPasswordException {
        User user = userMapper.getUserByName(username);
        if(user == null){
            log.info("user not exists for {}", username);
            throw new UserNotExistException();
        }
        String fullname = user.getFullname();
        int isFirstLogin =0;
//        System.out.println("-----"+fullname);
        if (fullname==null){
            isFirstLogin=1; //first login
        }
        String expectedPsw = HashUtil.MD5(password + user.getSalt());
        if(!expectedPsw.equals(user.getPassword())){
            throw new IncorrectPasswordException("incorrect password");
        }
        String token = TicketUtil.setTicket(user,redisTemplate,rememberMe);
        return new TokenAndLogin().setToken(token).setLogin(isFirstLogin);
    }



    public User getUserInfo(Integer id) {
        return userMapper.getUserById(id);
    }

    public List<User> getUserInfoByIds(List<Integer> ids){
        return userMapper.getUserByIds(ids);
    }

    public void insertUser(String headUrl) {
        User u = new User().setHeadUrl(headUrl).setId(hostHolder.getUser().getId());
        userMapper.updateUser(u);
    }

    public User addAndGetShakeUser() throws InterruptedException {
        TicketUtil.addShakeUser(hostHolder.getUser(),redisTemplate );

        Thread.sleep(6000);

        return TicketUtil.getShakeUser(hostHolder.getUser(),redisTemplate);
    }

    public List<User> findNearBy(double longitude, double latitude) {
//        update user,
        User u = hostHolder.getUser();
        u.setLongitude(longitude);
        u.setLatitude(latitude);
        userMapper.updateUserLocation(u);
        List<User> users = userMapper.findAll();

        // find all user and sort by distance;
        users.remove(u);
        users.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                double distance1 = Math.pow(o1.getLongitude()-longitude, 2)+Math.pow(o1.getLatitude()-latitude, 2);
                double distance2 = Math.pow(o2.getLongitude()-longitude, 2)+Math.pow(o2.getLatitude()-latitude, 2);
                return distance1-distance2>0?1:-1;
            }
        });
        return users;
    }


    public void sendSimpleMail(String to,String title,String content){
        log.info("sendSimpleMail in "+ "to: "+ to + "content: "+ content);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(title);
        message.setText(content);
        mailSender.send(message);
        log.info("send email success!");
    }



    public void updateUser(User user) throws Exception{
        User u = hostHolder.getUser();
        user.setId(u.getId());
        userMapper.updateUserInfo(user);
    }
}
