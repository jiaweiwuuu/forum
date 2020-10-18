package touch.forum.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import touch.forum.ForumApplication;

@SpringBootTest(classes = ForumApplication.class)
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void test(){
        //redisTemplate.opsForValue().set("template","test");
        redisTemplate.opsForSet().add("set",1,2,3,4);
    }
}
