package touch.forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Calendar;

@Configuration
public class BeanConfig {
    @Bean
    public Calendar getCalendar(){
        Calendar calendar = Calendar.getInstance();
        return calendar;
    }

}
