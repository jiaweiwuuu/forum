package touch.forum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import touch.forum.entity.Comment;
import touch.forum.entity.Message;

import java.util.List;

@Mapper
@Repository
public interface MessageMapper {

    int create(Message message);

    int messageRead(String conversationId);

    List<Message> getMessagesByConversationId(String conversationId);


    List<Message> getMessageByIds(List<Integer> messageIds);
}
