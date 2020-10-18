package touch.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import touch.forum.interceptor.LoginRequireInterceptor;
import touch.forum.interceptor.PassportInterceptor;

@Configuration
public class InterceptorConfiguraton implements WebMvcConfigurer {
    @Autowired
    PassportInterceptor passportInterceptor;
    @Autowired
    LoginRequireInterceptor loginRequireInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(passportInterceptor);
        registry.addInterceptor(loginRequireInterceptor);
    }
}
