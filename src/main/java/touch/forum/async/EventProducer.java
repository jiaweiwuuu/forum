package touch.forum.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    String EVENT_QUEUE_KEY = "EVENT_QUEUE";
    public boolean fireEvent(EventModel model){
        try{
            redisTemplate.opsForList().leftPush(EVENT_QUEUE_KEY,model);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}
