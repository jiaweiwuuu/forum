package touch.forum.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import touch.forum.entity.HostHolder;
import touch.forum.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Slf4j
public class LoginRequireInterceptor implements HandlerInterceptor {
    @Autowired
    HostHolder hostHolder;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(hostHolder.getUser() == null){
            log.info("not logged in");
            // redirect
        }else{
            log.info("logged in");
        }
        return true;
    }
}
