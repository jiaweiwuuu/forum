package touch.forum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import touch.forum.entity.Contact;
import touch.forum.entity.User;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface ContactMapper {
    int addContact(Contact contact);

    int updateContact(Contact contact);

    List<Contact> getContact(String conversationId);

    List<Contact> getContactList(String id);


}
