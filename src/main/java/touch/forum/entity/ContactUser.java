package touch.forum.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ContactUser {

    private int id;
    private String name;
    private String headUrl;
    private String latestMessage;

}
