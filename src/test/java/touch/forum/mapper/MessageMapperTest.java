package touch.forum.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import touch.forum.ForumApplication;
import touch.forum.entity.Message;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ForumApplication.class)
class MessageMapperTest {
    @Autowired
    Calendar calendar;
    @Autowired
    MessageMapper messageMapper;
    @Test
    void create() {
        Message message = new Message().setToId(3).setContent("hello").setFromId(7).setCreateAt(calendar.getTime()).setConversationId("37").setHasRead(false);
        messageMapper.create(message);
    }

    @Test
    void getMessagesByConversationId() {
        List<Message> messageList = messageMapper.getMessagesByConversationId("37");
        messageList.forEach(x -> System.out.println(x));
    }
}