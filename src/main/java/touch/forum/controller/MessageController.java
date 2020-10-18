package touch.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import touch.forum.ResultVO;
import touch.forum.entity.HostHolder;
import touch.forum.entity.Message;
import touch.forum.service.MessageService;
import touch.forum.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
//    @Autowired
//    MessageService messageService;
//    @Autowired
//    HostHolder hostHolder;
//
//    @PostMapping("/send")
//    public ResultVO<Object> sendMessage(@RequestParam("to_id") Integer toId,
//                                        @RequestParam("content") String content){
//        int result = messageService.createMessage(hostHolder.getUser().getId(),toId,content);
//        return ResponseUtil.makeSuccessResponse();
//    }
//
//    @GetMapping("/list")
//    public ResultVO<Object> getMessages(@RequestParam("to_id") Integer toId){
//        List<Message> result = messageService.getMessageBetweenUsers(hostHolder.getUser().getId(),toId);
//        return ResponseUtil.makeSuccessResponse(result);
//    }
}
