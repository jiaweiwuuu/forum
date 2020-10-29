package touch.forum.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import touch.forum.entity.LoginTicket;
import touch.forum.entity.User;

import java.awt.*;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class TicketUtil {
    public static int DEFAULT_TIMEOUT = 300;
    public static int LONG_TIMEOUT = 3000;
    public static int SHAKE_TIME =15;
    public static String SHAKE = "shake";


    public static String setTicket(User user, RedisTemplate template, boolean remember){
        int timeout = remember? LONG_TIMEOUT:DEFAULT_TIMEOUT;
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setUsername(user.getName());
        String token = UUID.randomUUID().toString().replaceAll("-","");
        template.opsForValue().set(token,loginTicket,timeout, TimeUnit.HOURS);
        return token;
    }


    public static void addShakeUser(User user, RedisTemplate template){
//        value: String, list,map, set Lset,
        // 获取所有
//        Set members = template.opsForSet().members(SHAKE);
//        LoginTicket loginTicket = new LoginTicket();
//        loginTicket.setUserId(user.getId());
//        loginTicket.setUsername(user.getName());
        template.opsForSet().add(SHAKE, user);
        template.expire(SHAKE,SHAKE_TIME, TimeUnit.SECONDS);

    }

    public static User getShakeUser(User user, RedisTemplate template){
        Set members = template.opsForSet().members(SHAKE);
        if (members.size()==1){
            return null;
        }
        for (Object member:members){
            User peer = ((User)member);
            if (peer.getId()!=user.getId()){
                return peer;
            }
        }
        return null;
    }
}
