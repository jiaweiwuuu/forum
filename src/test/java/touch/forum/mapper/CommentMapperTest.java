package touch.forum.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import touch.forum.ForumApplication;
import touch.forum.entity.Comment;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ForumApplication.class)
class CommentMapperTest {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    Calendar calendar;
    @Test
    void create() {
        Comment comment = new Comment();
        comment.setUserId(3).setContent("this is a comment again").setEntityId(15).setEntityType(0).setCreateAt(calendar.getTime());
        commentMapper.create(comment);
    }

    @Test
    void getCommentById() {
        Comment comment = commentMapper.getCommentById(1);
        System.out.println(comment);
    }

    @Test
    void getCommentByEntity(){
        List<Comment> comments = commentMapper.getCommentByEntityId(15,0);
        comments.forEach(System.out::println);
    }

    @Test
    void getCommentCount(){
        int count = commentMapper.getCommentCount(15,0);
        System.out.println(count);
    }
}