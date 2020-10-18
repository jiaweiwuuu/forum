package touch.forum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import touch.forum.entity.Comment;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    int create(Comment comment);

    Comment getCommentById(int id);

    List<Comment>getCommentByEntityId(int entityId, int entityType);

    int getCommentCount(int entityId, int entityType);

}
