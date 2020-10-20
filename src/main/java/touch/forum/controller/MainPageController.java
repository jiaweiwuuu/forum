package touch.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import touch.forum.ResultVO;
import touch.forum.consts.ResponseEnum;
import touch.forum.entity.Question;
import touch.forum.service.QuestionService;
import touch.forum.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/main")
public class MainPageController {
    @Autowired
    QuestionService service;

    @GetMapping("/news")
    public ResultVO<Object> GetNews() {
        List<Question> questions = service.getAllQuestions();

        return ResponseUtil.makeSuccessResponse(questions);
    }
}
