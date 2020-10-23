package touch.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import touch.forum.ResultVO;
import touch.forum.consts.ResponseEnum;
import touch.forum.entity.HostHolder;
import touch.forum.entity.Question;
import touch.forum.service.QuestionService;
import touch.forum.utils.FileUtil;
import touch.forum.utils.ResponseUtil;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    QuestionService service;


    @PostMapping("/createWithImage")
    public ResultVO<Object> createQuestionWithImage(
                                @RequestParam String title,
                                @RequestParam String content,
                                @RequestParam(value = "files") MultipartFile[] files) {
        int questionId;
        try{
            questionId = service.createQuestion(title,content,hostHolder.getUser());
            if (files != null && files.length >= 1) {
                for (MultipartFile file : files) {
                    String fileUrl = FileUtil.uploadFile(file);
                    service.insertQuestionImage(questionId, fileUrl);
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.makeErrorResponse(ResponseEnum.NotLoginError);
        }
        return ResponseUtil.makeSuccessResponse(questionId);

    }


    @PostMapping("/create")
    public ResultVO<Object> createQuestion(@RequestParam String title,
                                   @RequestParam String content) {
        int id;
        try{
            id = service.createQuestion(title,content,hostHolder.getUser());
        }
        catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.makeErrorResponse(ResponseEnum.NotLoginError);
        }
        return ResponseUtil.makeSuccessResponse(id);

    }

    @GetMapping("/detail")
    public ResultVO<Object> QuestionDetail(@RequestParam("qid") int id){
        Question question = service.getQuestion(id);

        return ResponseUtil.makeSuccessResponse(question);
    }

    @GetMapping("/newsDetailByUserId")
    public ResultVO<Object> QuestionDetailByUserId(@RequestParam("uid") int uid){
        List<Question> questions = service.getQuestionByUserId(uid);
        return ResponseUtil.makeSuccessResponse(questions);
    }




}
