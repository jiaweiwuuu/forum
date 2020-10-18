package touch.forum.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import touch.forum.ForumApplication;
import touch.forum.mapper.UserMapper;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = ForumApplication.class)
class UserServiceTest {
    @Autowired
    UserService service;

    @Test
    void createUser() {
        service.createUser("jiawei123","1212");
    }
}