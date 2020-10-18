package touch.forum.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touch.forum.entity.Feed;
import touch.forum.mapper.FeedMapper;

import java.util.List;

@Service
@Slf4j
public class FeedService {

    @Autowired
    FeedMapper feedMapper;

    @Autowired
    FollowService followService;
    public int createFeed(Feed feed){
        log.info("creating feed : [{}]",feed.toString());
        return feedMapper.createFeed(feed);
    }

    public List<Feed> selectUserFeeds(int maxId,int count, int userId){
        List<Integer> followeeIds = followService.getFollowees(2,userId,10);
        return feedMapper.selectUserFeeds(maxId,followeeIds,count);
    }


    public Feed getFeedbyId(int id){
        return feedMapper.getFeedbyId(id);
    }


}
