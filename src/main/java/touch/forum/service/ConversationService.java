package touch.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touch.forum.entity.ContactUser;
import touch.forum.entity.Contact;
import touch.forum.entity.Message;
import touch.forum.entity.User;
import touch.forum.utils.ConversationUtil;

import java.util.ArrayList;
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
        int messageId = messageService.createMessage(conversationId,content, fromId,toId);
        List<Contact> contact = contactService.getContact(conversationId);
        if(contact.size() == 0){
            return contactService.addContact(conversationId,messageId);
        }
        else{
            return contactService.updateContact(contact.get(0).setLatestMessageId(messageId));
        }
    }

    public List<Message> getMessage(int fromId, int toId){
        String conversationId = ConversationUtil.getConversationId(fromId,toId);
        return messageService.getMessage(conversationId);
    }

    public List<ContactUser> getContactList(int fromId){
        List<Contact> contactList = contactService.getContactList(fromId);
        List<Integer> userIds = new ArrayList<>();
        List<Integer> messageIds = new ArrayList<>();

        for(Contact contact : contactList){
            userIds.add(ConversationUtil.getContacterId(String.valueOf(fromId),contact.getConversationId()));
            messageIds.add(contact.getLatestMessageId());
        }
        List<User> userList = new ArrayList<>();
        List<Message> messageList = new ArrayList<>();
        if(userIds.size() != 0){
            userList = userService.getUserInfoByIds(userIds);
            messageList = messageService.getMessageByIds(messageIds);
        }

        userList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return userIds.indexOf(o1.getId()) - userIds.indexOf(o2.getId());
            }
        });

        messageList.sort(new Comparator<Message>() {
            @Override
            public int compare(Message o1, Message o2) {
                return messageIds.indexOf(o1.getId()) - messageIds.indexOf(o2.getId());
            }
        });
        List<ContactUser> contactUserList = new ArrayList<>();
        for(int i=0; i < userList.size(); i++){
            User user = userList.get(i);
            Message message = messageList.get(i);
            contactUserList.add(new ContactUser().setName(user.getName()).setHeadUrl(user.getHeadUrl()).setId(user.getId()).setLatestMessage(message.getContent()));
        }
        return contactUserList;
    }

    public int createConversationWithImage(int fromId, Integer toId, String imageUrl) {
        String conversationId = ConversationUtil.getConversationId(fromId,toId);

        int messageId = messageService.createMessageWithImage(conversationId,imageUrl, fromId,toId);
        List<Contact> contact = contactService.getContact(conversationId);
        if(contact.size() == 0){
            return contactService.addContact(conversationId,messageId);
        }
        else{
            return contactService.updateContact(contact.get(0).setLatestMessageId(messageId));
        }
    }
}
