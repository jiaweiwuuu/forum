package touch.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import touch.forum.ResultVO;
import touch.forum.async.EventModel;
import touch.forum.async.EventProducer;
import touch.forum.async.EventType;
import touch.forum.entity.Comment;
import touch.forum.entity.HostHolder;
import touch.forum.entity.Question;
import touch.forum.service.CommentService;
import touch.forum.service.QuestionService;
import touch.forum.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/comment")

public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    EventProducer eventProducer;

    @PostMapping("/create")
    public ResultVO<Object> createComment(@RequestParam("entity_id") int entityId,
                                          @RequestParam("entity_type") int entityType,
                                          @RequestParam("content") String content){
        int id = commentService.addComment(content,entityId,entityType,hostHolder.getUser());
        EventModel eventModel = new EventModel().setEventType(EventType.COMMENT).setActorId(hostHolder.getUser().getId()).setEntityId(entityId).setEntityType(entityType).setEntityOwner(0);
        eventProducer.fireEvent(eventModel);
        return ResponseUtil.makeSuccessResponse(id);
    }


    @GetMapping("/list")
    public ResultVO<Object> getComment(@RequestParam("entity_id") int entityId,
                                       @RequestParam("entity_type") int entityType){
        List<Comment> commentList = commentService.getCommentsByEntity(entityId,entityType);
        return ResponseUtil.makeSuccessResponse(commentList);
    }

}
