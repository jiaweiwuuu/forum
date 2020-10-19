package touch.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touch.forum.entity.Message;
import touch.forum.mapper.CommentMapper;
import touch.forum.mapper.MessageMapper;

import java.util.Calendar;
import java.util.List;


@Service
public class MessageService {
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    QuestionService questionService;
    @Autowired
    Calendar calendar;

    public int sendMessage(Message message){
        return messageMapper.addMessage(message);
    }

    public List<Message> getMessagesByConversationId(String conversationId){
        return messageMapper.getMessagesByConversationId(conversationId);
    }
}
