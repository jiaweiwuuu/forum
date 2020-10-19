package touch.forum.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import touch.forum.ForumApplication;
import touch.forum.entity.Contact;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ForumApplication.class)
class ContactMapperTest {
    @Autowired
    Calendar calendar;
    @Autowired
    ContactMapper contactMapper;
    @Test
    void addContact() throws InterruptedException {
        Contact contact = new Contact().setConversationId("9-10").setCreateAt(calendar.getTime()).setUpdateAt(calendar.getTime());
        contactMapper.addContact(contact);

        Contact contact1 = new Contact().setConversationId("8-10").setCreateAt(calendar.getTime()).setUpdateAt(calendar.getTime());
        contactMapper.addContact(contact1);
    }

    @Test
    void updateContact(){
        List<Contact> result = contactMapper.getContact("10-12");
        Contact contact = result.get(0).setUpdateAt(calendar.getTime());
        contactMapper.updateContact(contact);
    }

    @Test
    void getContact(){
        List<Contact> result = contactMapper.getContact("10-12");
        result.forEach(x -> System.out.println(x));
    }

    @Test
    void getContactById(){
        List<Contact> result = contactMapper.getContactList("10");
        result.forEach(x -> System.out.println(x));
    }
}