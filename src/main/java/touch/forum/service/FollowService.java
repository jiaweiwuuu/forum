package touch.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import touch.forum.utils.RedisKeyUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Service
public class FollowService {
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Autowired
    Calendar calendar;

    public boolean follow(int userId, int entityId, int entityType){
        String followerKey = RedisKeyUtil.getFollowerKey(entityId,entityType);
        String followeeKey = RedisKeyUtil.getFolloweeKey(userId,entityType);

        redisTemplate.opsForZSet().add(followerKey,userId,calendar.getTimeInMillis());
        redisTemplate.opsForZSet().add(followeeKey,entityId,calendar.getTimeInMillis());
        return true;
    }

    public boolean unfollow(int userId, int entityId, int entityType){
        String followerKey = RedisKeyUtil.getFollowerKey(entityId,entityType);
        String followeeKey = RedisKeyUtil.getFolloweeKey(userId,entityType);

        redisTemplate.opsForZSet().remove(followerKey,userId);
        redisTemplate.opsForZSet().remove(followeeKey,entityId);
        return true;
    }

    public List<Integer> getFollowers(int entityType,int offset ,int entityId, int count){
        List<Integer> result = new ArrayList<>();
        String followerKey = RedisKeyUtil.getFollowerKey(entityId,entityType);
        Set<Object> range = redisTemplate.opsForZSet().range(followerKey, offset, count);
        for(Object id : range){
            result.add((Integer)id);
        }
        return result;
    }

    public List<Integer> getFollowers(int entityType, int entityId, int count){
        return getFollowers(entityType,0,entityId,count);
    }


    public List<Integer> getFollowees(int entityType,int offset ,int userId, int count){
        List<Integer> result = new ArrayList<>();
        String followeeKey = RedisKeyUtil.getFolloweeKey(userId,entityType);
        Set<Object> range = redisTemplate.opsForZSet().range(followeeKey, offset, count);
        for(Object id : range){
            result.add((Integer)id);
        }
        return result;
    }

    public List<Integer> getFollowees(int entityType, int userId, int count){
        return getFollowees(entityType,0,userId,count);
    }
}
