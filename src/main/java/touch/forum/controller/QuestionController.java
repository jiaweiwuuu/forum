package touch.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import touch.forum.ResultVO;
import touch.forum.entity.HostHolder;
import touch.forum.entity.Question;
import touch.forum.service.QuestionService;
import touch.forum.utils.ResponseUtil;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    QuestionService service;
    @PostMapping("/create")
    public ResultVO<Object> createQuestion(@RequestParam String title,
                                   @RequestParam String content) {
        int id;
        try{
            id = service.createQuestion(title,content,hostHolder.getUser());
        }
        catch (Exception e){
            return ResponseUtil.makeErrorResponse();
        }
        return ResponseUtil.makeSuccessResponse(id);

    }

    @GetMapping("/detail")
    public ResultVO<Object> QuestionDetail(@RequestParam("qid") int id){
        Question question = service.getQuestion(id);
        return ResponseUtil.makeSuccessResponse(question);
    }



}
