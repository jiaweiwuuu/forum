package touch.forum.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import touch.forum.entity.LoginTicket;
import touch.forum.entity.User;
import touch.forum.mapper.UserMapper;
import touch.forum.utils.HashUtil;
import touch.forum.utils.TicketUtil;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserService {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Autowired
    UserMapper userMapper;
    public static String DEFAULT_HEAD_URL = "www.baidu.com";
    public String createUser(String username, String password){
        User user = new User();
        user.setName(username).setSalt(UUID.randomUUID().toString().substring(0,5)).setHeadUrl(DEFAULT_HEAD_URL);
        user.setPassword(HashUtil.MD5(password+user.getSalt()));
        log.info("get user name when create [{}]",user.getName());
        userMapper.addUser(user);
        user.setId(userMapper.getUserByName(username).getId());
        String token = TicketUtil.setTicket(user,redisTemplate,false);
//        userMapper.addUser(user);
        return token;
    }

    public String login(String username, String password, boolean rememberMe){
        User user = userMapper.getUserByName(username);
        if(user == null){
            throw new RuntimeException("unfound username");
        }
        String expectedPsw = HashUtil.MD5(password + user.getSalt());
        if(!expectedPsw.equals(user.getPassword())){
            throw new RuntimeException("incorrect password");
        }
        String token = TicketUtil.setTicket(user,redisTemplate,rememberMe);
        return token;
    }
}
