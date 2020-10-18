package touch.forum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import touch.forum.entity.Feed;

import java.util.List;

@Mapper
@Repository
public interface FeedMapper {
    int createFeed(Feed feed);

    List<Feed> selectUserFeeds(int maxId, List<Integer> userId, int count);


    Feed getFeedbyId(int id);
}
