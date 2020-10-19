package touch.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import touch.forum.consts.EntityEnum;
import touch.forum.entity.Comment;
import touch.forum.entity.Question;
import touch.forum.entity.User;
import touch.forum.mapper.CommentMapper;

import java.util.Calendar;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    QuestionService questionService;

    public List<Comment> getCommentsByEntity(int entityId, int entityType){
        return commentMapper.getCommentByEntityId(entityId,entityType);
    }

    public int addComment(String content, int entityId, int entityType, User user){
         Comment comment = new Comment().setContent(content).setCreateAt(Calendar.getInstance().getTime()).setEntityId(entityId).setEntityType(entityType).setUserId(user.getId());
         int i = commentMapper.create(comment);
         int count = commentMapper.getCommentCount(entityId,entityType);
         if(entityType == EntityEnum.QUESTION.getValue()) {
             Question question = questionService.getQuestion(entityId);
             question.setCommentCount(count);
             questionService.updateQuestion(question);
         }
         return i == 0 ? 0 : comment.getId();
    }

    public int getCommentCount(int entityId, int entityType){
        return commentMapper.getCommentCount(entityId,entityType);
    }
}
