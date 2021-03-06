package touch.forum.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import touch.forum.ResultVO;
import touch.forum.consts.ResponseEnum;
import touch.forum.entity.ContactUser;
import touch.forum.entity.HostHolder;
import touch.forum.entity.Message;
import touch.forum.entity.User;
import touch.forum.service.ConversationService;
import touch.forum.service.MessageService;
import touch.forum.utils.FileUtil;
import touch.forum.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private ConversationService conversationService;

    @PostMapping("/send")
    public ResultVO<Object> sendMessage(@RequestParam("to_id") Integer toId,
                                        @RequestParam("content") String content){
        try{
            int result = conversationService.createConversation(hostHolder.getUser().getId(),toId,content);
        }
        catch (NullPointerException e){
            return ResponseUtil.makeErrorResponse(ResponseEnum.NotLoginError);
        }
        return ResponseUtil.makeSuccessResponse();
    }

    @PostMapping("/sendImage")
    public ResultVO<Object> sendImage(@RequestParam("to_id") Integer toId,
                                        @RequestParam(value = "file") MultipartFile file){
        String imageUrl = FileUtil.uploadFile(file);
        try{
            int result = conversationService.createConversationWithImage(hostHolder.getUser().getId(),toId,imageUrl);
        }
        catch (NullPointerException e){
            return ResponseUtil.makeErrorResponse(ResponseEnum.NotLoginError);
        }
        return ResponseUtil.makeSuccessResponse();


    }

    @GetMapping("/all")
    public ResultVO<Object> getMessage(@RequestParam("to_id") Integer toId){
        List<Message> messageList = null;
        try{
            messageList = conversationService.getMessage(hostHolder.getUser().getId(),toId);
        }
        catch (NullPointerException e){
            return ResponseUtil.makeErrorResponse(ResponseEnum.NotLoginError);
        }
        return ResponseUtil.makeSuccessResponse(messageList);
    }

    @GetMapping("/contact")
    public ResultVO<Object> getContactList(){
        List<ContactUser> userList = null;
        try{
            userList = conversationService.getContactList(hostHolder.getUser().getId());
        }catch (NullPointerException e){
            return ResponseUtil.makeErrorResponse(ResponseEnum.NotLoginError);
        }
        return ResponseUtil.makeSuccessResponse(userList);
    }
}
