package touch.forum.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import touch.forum.ForumApplication;
import touch.forum.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = ForumApplication.class)
class UserServiceTest {
    @Autowired
    UserService userService;
    @Test
    void getUserInfoByIds() {
        List<Integer> ids = new ArrayList<>();
        ids.add(17);
        ids.add(18);
        ids.add(19);

        List<User> userList = userService.getUserInfoByIds(ids);
        userList.forEach(x -> System.out.println(x));
    }
}