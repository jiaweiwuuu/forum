package touch.forum.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

@Data
@Accessors(chain = true)
public class User {
    @Id
    private int id;
    private String name;
    private String password;
    private String salt;
    private String headUrl;

}
