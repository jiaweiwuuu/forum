package touch.forum.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Comment {
    @Id
    private int id;
    private String content;
    private int entityId;
    private int entityType;
    private Date createAt;
    private int userId;
}
