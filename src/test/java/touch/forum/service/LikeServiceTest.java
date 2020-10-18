package touch.forum.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import touch.forum.ForumApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ForumApplication.class)
class LikeServiceTest {
    @Autowired
    LikeService service;
    @Test
    void like() {
        service.like(4,15,0);
    }

    @Test
    void unlike() {
        service.unlike(3,15,0);
    }
}