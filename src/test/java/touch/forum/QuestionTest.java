package touch.forum;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import touch.forum.entity.Question;
import touch.forum.entity.User;
import touch.forum.mapper.QuestionMapper;
import touch.forum.mapper.UserMapper;

import java.util.Calendar;
import java.util.List;

@SpringBootTest(classes = ForumApplication.class)
public class QuestionTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    Calendar calendar;
    @Test
    public void getQuestion(){
//        User user = new User();
//        user.setName("nae").setPassword("111").setSalt("sss").setHeadUrl("head");
//        userMapper.addUser(user);
        Question question = new Question().setContent("11").setCreateAt(calendar.getTime()).setCommentCount(0).setTitle("ss").setUserId(3);
        questionMapper.create(question);
    }
}
