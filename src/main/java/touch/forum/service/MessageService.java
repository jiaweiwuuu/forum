package touch.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touch.forum.entity.Message;
import touch.forum.mapper.MessageMapper;

import java.util.Calendar;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    Calendar calendar;
    public int createMessage(int fromId, int toId, String content){
        Message message = new Message().setHasRead(false).setCreateAt(calendar.getTime()).setFromId(fromId).setToId(toId).setContent(content);
        String conversationId = getConversationId(fromId,toId);
        message.setConversationId(conversationId);
        return messageMapper.create(message);
    }

    public List<Message> getMessageBetweenUsers(int fromId, int toId){
        String conversationId = getConversationId(fromId,toId);
        return messageMapper.getMessagesByConversationId(conversationId);
    }

    private String getConversationId(int fromId, int toId){
        String conversationId;
        if(fromId < toId){
            conversationId = fromId + "-" + toId;
        }
        else{
            conversationId = toId + "-" + fromId;
        }
        return conversationId;
    }

}
