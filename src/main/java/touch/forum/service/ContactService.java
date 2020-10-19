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

    public int addContact(String conversationId) {
        Calendar calendar = Calendar.getInstance();
        Contact contact = new Contact().setConversationId(conversationId).setCreateAt(calendar.getTime()).setUpdateAt(calendar.getTime());
        return contactMapper.addContact(contact);
    }

    public List<Contact> getContact(String conversationId) {
        return contactMapper.getContact(conversationId);
    }

    public int updateContact(Contact contact) {
        contact.setUpdateAt(Calendar.getInstance().getTime());
        return contactMapper.updateContact(contact);
    }

    public List<Integer> getContactList(int id) {
        List<Integer> contactIdList = new ArrayList<>();
        List<Contact> contactList = contactMapper.getContactList(String.valueOf(id));
        contactList.forEach(x -> contactIdList.add(ConversationUtil.getContacterId(String.valueOf(id), x.getConversationId())));
        return contactIdList;
    }
}
