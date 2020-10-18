package touch.forum.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Feed {
    private int id;
    private int type;
    private int userId;
    private Date createAt;

    //JSON 格式
    private String data;
}
