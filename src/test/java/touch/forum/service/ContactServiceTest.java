package touch.forum.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import touch.forum.ForumApplication;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ForumApplication.class)
class ContactServiceTest {
    @Autowired
    ContactService contactService;
    @Test
    void getContactList() {
        List<Integer> ids = contactService.getContactList(10);
        System.out.println(ids);
    }
}