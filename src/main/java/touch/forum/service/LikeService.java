package touch.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    private static String LIKE = "LIKE";
    private static String SPLIT = "_";

    public long like(int userId, int entityId, int entityType){
        String key = LIKE + SPLIT+entityId+SPLIT+entityType;
        redisTemplate.opsForSet().add(key,userId);
        return redisTemplate.opsForSet().size(key);
    }
    public long unlike(int userId, int entityId, int entityType){
        String key = LIKE + SPLIT+entityId+SPLIT+entityType;
        redisTemplate.opsForSet().remove(key,userId);
        return redisTemplate.opsForSet().size(key);
    }

    public boolean getLikeStatus(int userId, int entityId, int entityType){
        String key = LIKE + SPLIT+entityId+SPLIT+entityType;
        return redisTemplate.opsForSet().isMember(key, userId);
    }
}
