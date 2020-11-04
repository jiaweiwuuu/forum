package touch.forum.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import touch.forum.entity.Question;
import touch.forum.entity.QuestionImage;
import touch.forum.entity.User;
import touch.forum.mapper.QuestionMapper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
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
        Question q=  questionMapper.getQuestionById(id);
        ArrayList<QuestionImage> images = q.getImages();
        if (images.size()==1 && images.get(0).getImageUrl()==null){
            log.info("imageurl in null is {}", images.get(0).getImageUrl() );
            q.setImages(new ArrayList<>());
        }
        return q;
    }

    public List<Question> getQuestionByUserId(int uid){
        List<Question> qs = questionMapper.getQuestionByUserId(uid);
        // 因为自动映射的时候，会映射一个有默认值的image， 下面的语句改为空；
        return removeNullImage(qs);
    }

    private List<Question> removeNullImage(List<Question> qs){
        for (Question q: qs){
            ArrayList<QuestionImage> images = q.getImages();
            if (images.size()==1 && images.get(0).getImageUrl()==null){
                q.setImages(new ArrayList<>());
            }
        }
        return qs;
    }

    public List<Question> getAllQuestions(){
        List<Question> qs = questionMapper.getAllQuestions();
        // 因为自动映射的时候，会映射一个有默认值的image， 下面的语句改为空；
        return removeNullImage(qs);
    }

    public int updateQuestion(Question question){
        return questionMapper.update(question);
    }

    public void insertQuestionImage(int questionId, String imageUrl) {
        QuestionImage image = new QuestionImage().setQuesionId(questionId).setImageUrl(imageUrl);
        questionMapper.insertImage(image);
    }

    public int createQuestion(String title, String content, User user, String address, double longitude, double latitude) {
        content = HtmlUtils.htmlEscape(content);
        title = HtmlUtils.htmlEscape(title);

        if(user == null){
            throw new RuntimeException("user should login");
        }
        Question question = new Question().setUserId(user.getId()).
                setTitle(title).setContent(content).setCommentCount(0).
                setCreateAt(Calendar.getInstance().getTime()).
                setAddress(address).setLongitude(longitude).setLatitude(latitude);

        questionMapper.create(question);
        return question.getId();
    }

    public List<Question> getQuestionByKeyword(String keyword) {
        return questionMapper.getQuestionsByContentOrTitle(keyword);
    }
}
