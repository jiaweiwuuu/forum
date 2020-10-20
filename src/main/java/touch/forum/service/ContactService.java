package touch.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touch.forum.entity.Contact;
import touch.forum.mapper.ContactMapper;
import touch.forum.utils.ConversationUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactMapper contactMapper;

    public int addContact(String conversationId, int latestMessageId) {
        Calendar calendar = Calendar.getInstance();
        Contact contact = new Contact().setConversationId(conversationId).setCreateAt(calendar.getTime()).setUpdateAt(calendar.getTime()).setLatestMessageId(latestMessageId);
        return contactMapper.addContact(contact);
    }

    public List<Contact> getContact(String conversationId) {
        return contactMapper.getContact(conversationId);
    }

    public int updateContact(Contact contact) {
        contact.setUpdateAt(Calendar.getInstance().getTime());
        return contactMapper.updateContact(contact);
    }

    public List<Contact> getContactList(int id) {
        List<Integer> contactIdList = new ArrayList<>();
        return contactMapper.getContactList(String.valueOf(id));
    }
}
