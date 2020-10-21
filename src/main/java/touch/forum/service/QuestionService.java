package touch.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import touch.forum.entity.Question;
import touch.forum.entity.QuestionImage;
import touch.forum.entity.User;
import touch.forum.mapper.QuestionMapper;

import java.util.Calendar;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    public int createQuestion(String title, String content, User user){

        content = HtmlUtils.htmlEscape(content);
        title = HtmlUtils.htmlEscape(title);

        if(user == null){
            throw new RuntimeException("user should login");
        }
        Question question = new Question().setUserId(user.getId()).setTitle(title).setContent(content).setCommentCount(0).setCreateAt(Calendar.getInstance().getTime());

        questionMapper.create(question);
        return question.getId();
    }

    public Question getQuestion(int id){
        return questionMapper.getQuestionById(id);
    }

    public List<Question> getAllQuestions(){
        return questionMapper.getAllQuestions();
    }

    public int updateQuestion(Question question){
        return questionMapper.update(question);
    }

    public void insertQuestionImage(int questionId, String imageUrl) {
        QuestionImage image = new QuestionImage().setQuesionId(questionId).setImageUrl(imageUrl);
        questionMapper.insertImage(image);
    }
}
