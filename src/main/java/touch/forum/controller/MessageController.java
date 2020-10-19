package touch.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import touch.forum.ResultVO;
import touch.forum.entity.Comment;
import touch.forum.entity.HostHolder;
import touch.forum.entity.Message;
import touch.forum.service.CommentService;
import touch.forum.service.MessageService;
import touch.forum.utils.ResponseUtil;

import java.util.Calendar;
import java.util.List;


@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService messageService;
    @Autowired
    HostHolder hostHolder;
    @Autowired
    Calendar calendar;

    @PostMapping("/create")
    public ResultVO<Object> sendMessage(@RequestParam("toUserId") int toUserId,
                                          @RequestParam("content") String content){

        Integer fromUserId = hostHolder.getUser().getId();

        int smallId = Math.min(fromUserId, toUserId);
        int largeId = Math.max(fromUserId, toUserId);
        //对话id是按照 "小的用户id- 大的用户id"。
        String conversationId = smallId+"-"+largeId;

        Message message = new Message().setFromId(fromUserId).setToId(toUserId).
                setContent(content).setCreateAt(calendar.getTime()).
                setConversationId(conversationId).setHasRead(false);

        int id = messageService.sendMessage(message);

        return ResponseUtil.makeSuccessResponse(id);
    }


    @GetMapping("/messages")
    List<Message> getMessagesByConversationId(@RequestParam("toUserId") int toUserId){
        int fromUserId = hostHolder.getUser().getId();
        int smallId = Math.min(fromUserId, toUserId);
        int largeId = Math.max(fromUserId, toUserId);
        //对话id是按照 "小的用户id- 大的用户id"。
        String conversationId = smallId+"-"+largeId;
        return messageService.getMessagesByConversationId(conversationId);
    }

}
