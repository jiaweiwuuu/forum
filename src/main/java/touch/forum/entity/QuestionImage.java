package touch.forum.entity;


import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Accessors(chain = true)
public class QuestionImage {
    @Id
    private int id;
    private int quesionId;
    private String imageUrl;

}
