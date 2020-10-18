package touch.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import touch.forum.ResultVO;
import touch.forum.entity.Feed;
import touch.forum.entity.HostHolder;
import touch.forum.service.FeedService;
import touch.forum.service.LikeService;
import touch.forum.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {
    @Autowired
    HostHolder hostHolder;
    @Autowired
    FeedService feedService;

    @GetMapping("/pullfeeds")
    public ResultVO<Object> pullFeeds(){
        List<Feed> feedList = feedService.selectUserFeeds(10,10,hostHolder.getUser().getId());
        return ResponseUtil.makeSuccessResponse(feedList);
    }

    @GetMapping("/pushfeeds")
    public ResultVO<Object> pushFeeds(){
        return null;
    }
}
