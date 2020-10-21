package touch.forum.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Message {
    private int id;
    private int fromId;
    private int toId;
    private String content;
    private Date createAt;
    private boolean hasRead;
    private String conversationId;
    private String imageUrl;
}
