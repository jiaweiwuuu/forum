package touch.forum.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import touch.forum.entity.HostHolder;
import touch.forum.entity.LoginTicket;
import touch.forum.entity.User;
import touch.forum.mapper.UserMapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Service
public class PassportInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = null;
        if(request.getCookies() != null){
            for(Cookie cookie : request.getCookies()){
                if(cookie.getName().equals("token")){
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if(token != null){
            log.info("get token [{}]",token);
            LoginTicket ticket = (LoginTicket) redisTemplate.opsForValue().get(token);
            if(ticket == null){
                return true;
            }
            else{
                log.info("get ticket [{}]",ticket.toString());
                User user = userMapper.getUserById(ticket.getUserId());
                log.info("get user [{}]",user.toString());
                hostHolder.setUser(user);
            }
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clear();
    }
}
