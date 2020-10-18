package touch.forum.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginTicket implements Serializable {
    private int userId;
    private String username;
}
