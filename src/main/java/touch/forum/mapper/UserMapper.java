package touch.forum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import touch.forum.entity.User;

@Mapper
@Repository
public interface UserMapper {
    int addUser(User user);

    User getUserById(int id);

    User getUserByName(String name);

    int updatePassword(User user);
}
