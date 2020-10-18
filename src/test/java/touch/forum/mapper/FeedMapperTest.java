package touch.forum.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import touch.forum.ForumApplication;
import touch.forum.entity.Feed;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ForumApplication.class)
class FeedMapperTest {

    @Autowired
    FeedMapper feedMapper;
    @Autowired
    Calendar calendar;

    @Test
    void createFeed() {
        Feed feed = new Feed().setCreateAt(calendar.getTime()).setData("test").setType(1).setUserId(2);
        feedMapper.createFeed(feed);
    }

    @Test
    void selectUserFeeds() {
        List<Feed> feeds = feedMapper.selectUserFeeds(10,Arrays.asList(2,8),10);
        feeds.forEach(x -> System.out.println(x));
    }

    @Test
    void getFeedbyId() {
        Feed feed = feedMapper.getFeedbyId(1);
        System.out.println(feed);
    }
}