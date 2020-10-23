package touch.forum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import touch.forum.entity.Question;
import touch.forum.entity.QuestionImage;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
//    List<Question> getQuestionByUserId(int userId, int offset, int limit);

    List<Question> getQuestionByUserId(int userId);

    List<Question> getAllQuestions();

    Question getQuestionById(int qid);
    int create(Question question);

    int update(Question question);


    void insertImage(QuestionImage image);

}
