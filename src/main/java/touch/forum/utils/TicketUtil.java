package touch.forum.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import touch.forum.entity.LoginTicket;
import touch.forum.entity.User;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TicketUtil {
    public static int DEFAULT_TIMEOUT = 300;
    public static int LONG_TIMEOUT = 3000;
    public static String setTicket(User user, RedisTemplate template, boolean remember){
        int timeout = remember? LONG_TIMEOUT:DEFAULT_TIMEOUT;
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setUsername(user.getName());
        String token = UUID.randomUUID().toString().replaceAll("-","");
        template.opsForValue().set(token,loginTicket,timeout, TimeUnit.SECONDS);
        return token;
    }
}
