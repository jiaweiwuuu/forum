package touch.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import touch.forum.entity.Question;
import touch.forum.entity.User;
import touch.forum.mapper.QuestionMapper;

import java.util.Calendar;

@Service
public class QuestionService {
    @Autowired
    Calendar calendar;
    @Autowired
    private QuestionMapper questionMapper;
    public void createQuestion(String title, String content, User user){
        // 过滤html标签
        content = HtmlUtils.htmlEscape(content);
        title = HtmlUtils.htmlEscape(title);
        //敏感词过滤 前缒树


        if(user == null){
            throw new RuntimeException("user should login");
        }
        Question question = new Question().setUserId(user.getId()).setTitle(title).setContent(content).setCommentCount(0).setCreateAt(calendar.getTime());

        questionMapper.create(question);
    }

    public Question getQuestion(int id){
        Question question = questionMapper.getQuestionById(id);
        return  question;
    }

    public int updateQuestion(Question question){
        return questionMapper.update(question);
    }
}
