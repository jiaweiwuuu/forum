package touch.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import touch.forum.ResultVO;
import touch.forum.entity.Comment;
import touch.forum.entity.HostHolder;
import touch.forum.service.CommentService;
import touch.forum.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/comment")

public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    HostHolder hostHolder;

    @PostMapping("/create")
    public ResultVO<Object> createComment(@RequestParam("entity_id") int entityId,
                                          @RequestParam("entity_type") int entityType,
                                          @RequestParam("content") String content){
        int id = commentService.addComment(content,entityId,entityType,hostHolder.getUser());

        return ResponseUtil.makeSuccessResponse(id);
    }


    @GetMapping("/list")
    public ResultVO<Object> getComment(@RequestParam("entity_id") int entityId,
                                       @RequestParam("entity_type") int entityType){
        List<Comment> commentList = commentService.getCommentsByEntity(entityId,entityType);
        return ResponseUtil.makeSuccessResponse(commentList);
    }

}
