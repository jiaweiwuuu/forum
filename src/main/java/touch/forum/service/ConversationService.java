package touch.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touch.forum.entity.Contact;
import touch.forum.entity.Message;
import touch.forum.entity.User;
import touch.forum.utils.ConversationUtil;

import java.util.Comparator;
import java.util.List;

@Service
public class ConversationService {

    @Autowired
    private MessageService messageService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    public int createConversation(int fromId,int toId,String content){
        String conversationId = ConversationUtil.getConversationId(fromId,toId);
        messageService.createMessage(conversationId,content, fromId,toId);
        List<Contact> contact = contactService.getContact(conversationId);
        if(contact.size() == 0){
            return contactService.addContact(conversationId);
        }
        else{
            return contactService.updateContact(contact.get(0));
        }
    }

    public List<Message> getMessage(int fromId, int toId){
        String conversationId = ConversationUtil.getConversationId(fromId,toId);
        return messageService.getMessage(conversationId);
    }

    public List<User> getContactList(int fromId){
        List<Integer> userIds = contactService.getContactList(fromId);
        List<User> userList = userService.getUserInfoByIds(userIds);
        userList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return userIds.indexOf(o1.getId()) - userIds.indexOf(o2.getId());
            }
        });
        return userList;
    }
}
