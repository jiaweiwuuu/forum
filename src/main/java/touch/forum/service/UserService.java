package touch.forum.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import touch.forum.entity.HostHolder;
import touch.forum.entity.LoginTicket;
import touch.forum.entity.Question;
import touch.forum.entity.User;
import touch.forum.exception.IncorrectPasswordException;
import touch.forum.exception.UserNotExistException;
import touch.forum.exception.UsernameExistException;
import touch.forum.mapper.UserMapper;
import touch.forum.utils.HashUtil;
import touch.forum.utils.TicketUtil;

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

    public static String DEFAULT_HEAD_URL = "www.baidu.com";
    public String createUser(String username, String password) throws UsernameExistException {
        User user = userMapper.getUserByName(username);
        if(user != null){
            throw new UsernameExistException("user name exist");
        }
        user = new User();
        user.setName(username).setSalt(UUID.randomUUID().toString().substring(0,5)).setHeadUrl(DEFAULT_HEAD_URL);
        user.setPassword(HashUtil.MD5(password+user.getSalt()));
        log.info("get user name when create [{}]",user.getName());
        userMapper.addUser(user);
        user.setId(userMapper.getUserByName(username).getId());
        String token = TicketUtil.setTicket(user,redisTemplate,false);
//        userMapper.addUser(user);
        return token;
    }

    public String login(String username, String password, boolean rememberMe) throws UserNotExistException, IncorrectPasswordException {
        User user = userMapper.getUserByName(username);
        if(user == null){
            throw new UserNotExistException();
        }
        String expectedPsw = HashUtil.MD5(password + user.getSalt());
        if(!expectedPsw.equals(user.getPassword())){
            throw new IncorrectPasswordException("incorrect password");
        }
        String token = TicketUtil.setTicket(user,redisTemplate,rememberMe);
        return token;
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
}
