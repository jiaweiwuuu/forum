package touch.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touch.forum.entity.Message;
import touch.forum.mapper.MessageMapper;
import touch.forum.utils.ConversationUtil;

import java.util.Calendar;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageMapper messageMapper;

    public int createMessage(String conversationId, String content, int fromId, int toId) {
        Message message = new Message().setHasRead(false).setCreateAt(Calendar.getInstance().getTime()).setFromId(fromId).setToId(toId).setContent(content);
        return messageMapper.create(message.setConversationId(conversationId));
    }

    public List<Message> getMessage(String conversationId){
        List<Message> messageList = messageMapper.getMessagesByConversationId(conversationId);
        messageMapper.messageRead(conversationId);
        return messageList;
    }



}
