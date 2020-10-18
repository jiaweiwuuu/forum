package touch.forum.async.handlers;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import touch.forum.async.EventHandler;
import touch.forum.async.EventModel;
import touch.forum.async.EventType;
import touch.forum.entity.Feed;
import touch.forum.entity.Question;
import touch.forum.mapper.FeedMapper;
import touch.forum.service.FeedService;
import touch.forum.service.FollowService;
import touch.forum.service.QuestionService;
import touch.forum.utils.RedisKeyUtil;

import java.util.*;

@Slf4j
@Service
public class FeedHandler implements EventHandler {
    @Autowired
    Calendar calendar;
    @Autowired
    FeedService feedService;
    @Autowired
    QuestionService questionService;

    @Autowired
    FollowService followService;
    @Autowired
    RedisTemplate<String,Object> redisTemplate;
    @Override
    public void doHandle(EventModel model) {
        log.info("sending feed!!!!!");
        Feed feed = new Feed().setUserId(model.getActorId()).setType(model.getEventType().getValue()).setCreateAt(calendar.getTime());
        feed.setData(buildFeedData(model));
        if(feed.getData() == null){
            return;
        }
        feedService.createFeed(feed);

        List<Integer> followers = followService.getFollowers(2,model.getActorId(),Integer.MAX_VALUE);

        for(int followerId : followers){
            String key = RedisKeyUtil.getTimelineKey(followerId);
            redisTemplate.opsForList().leftPush(key,feed.getId());
        }
    }

    private String buildFeedData(EventModel model){
        Map<String,String> map = new HashMap<>();
        map.put("userId",String.valueOf(model.getActorId()));
        if(model.getEventType() == EventType.COMMENT){
            Question question = questionService.getQuestion(model.getEntityId());
            map.put("questionId",String.valueOf(model.getEntityId()));
            map.put("questionTitle",question.getTitle());
            Gson gson = new Gson();
            return gson.toJson(map);
        }
        return null;
    }

    @Override
    public List<EventType> getSuppportEventTypes() {
        return Arrays.asList(EventType.COMMENT);

    }
}