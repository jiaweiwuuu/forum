package touch.forum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import touch.forum.entity.User;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    int addUser(User user);

    User getUserById(int id);

    User getUserByName(String name);

    int updatePassword(User user);

    List<User> getUserByIds(List<Integer> ids);
}
