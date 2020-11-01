package touch.forum.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Objects;

@Data
@Accessors(chain = true)
public class User {
    @Id
    private int id;
    private String name;
    private String password;
    private String salt;
    private String headUrl;
    private double longitude;
    private double latitude;

    // 0:female, 1:male
    private int gender;
    private String email;
    private Date birthday;
    private String university;
    private String major;
    private String targetCompany;
    private String targetJob;
    // 1: firstLogin, 0:not
    private int firstLogin;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
