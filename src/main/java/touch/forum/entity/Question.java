package touch.forum.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Question {
    @Id
    private int id;
    private String title;
    private String content;

    private int userId;
    private Date createAt;
    private int commentCount;
    private String imageUrl;
}
