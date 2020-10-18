package touch.forum.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import touch.forum.entity.Question;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {
    List<Question> getQuestion(int userId, int offset, int limit);
    Question getQuestionById(int qid);
    int create(Question question);

    int update(Question question);

}
