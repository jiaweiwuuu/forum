package touch.forum.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Contact {
    @Id
    private int id;
    private String conversationId;
    private Date createAt;
    private Date updateAt;
    private int latestMessageId;
}
