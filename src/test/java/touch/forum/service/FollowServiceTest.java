package touch.forum.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import touch.forum.ForumApplication;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = ForumApplication.class)
class FollowServiceTest {
    @Autowired
    FollowService service;
    private static int USERTYPE = 2;
    @Test
    void follow() {
        for(int i =3; i<= 7; i++ ){
            service.follow(i,8,USERTYPE);
        }
    }

    @Test
    void unfollow() {
    }

    @Test
    void getFollowers() {
        System.out.println(service.getFollowers(USERTYPE,7,10 ));
    }

    @Test
    void testGetFollowers() {
    }

    @Test
    void getFollowees() {
        System.out.println(service.getFollowees(USERTYPE,7,10 ));
    }

    @Test
    void testGetFollowees() {
    }
}