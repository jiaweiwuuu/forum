package touch.forum.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import touch.forum.ResultVO;
import touch.forum.consts.ResponseEnum;
import touch.forum.entity.HostHolder;
import touch.forum.entity.Question;
import touch.forum.entity.User;
import touch.forum.exception.IncorrectPasswordException;
import touch.forum.exception.UserNotExistException;
import touch.forum.exception.UsernameExistException;
import touch.forum.service.UserService;
import touch.forum.utils.FileUtil;
import touch.forum.utils.ResponseUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    @Autowired
    private UserService service;

    @Autowired
    private HostHolder hostHolder;

    @PostMapping("/signup")
    public ResultVO<Object> createUser(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {

        try{
            String token = service.createUser(username, password);
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
        }catch (UsernameExistException e){
            e.printStackTrace();
            return ResponseUtil.makeErrorResponse(ResponseEnum.UserNameExist);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.makeErrorResponse(ResponseEnum.Error);
        }
        return ResponseUtil.makeSuccessResponse();

    }

    @PostMapping("/signin")
    public ResultVO<Object> userLogin(@RequestParam String username,
                            @RequestParam String password,
                            @RequestParam(value = "rememberme", defaultValue = "false") boolean rememberMe,
                            HttpServletResponse response) {
        try {
            String token = service.login(username, password, rememberMe);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
        }catch (UserNotExistException e){
            return ResponseUtil.makeErrorResponse(ResponseEnum.UserNotExist);
        }catch (IncorrectPasswordException e){
            return ResponseUtil.makeErrorResponse(ResponseEnum.IncorrectPassword);
        }
        return ResponseUtil.makeSuccessResponse();
    }

    @GetMapping("/userInfo")
    public ResultVO<Object> GetUserInfo(){
        User user = null;
        try{
            user = service.getUserInfo(hostHolder.getUser().getId());
        }
        catch (NullPointerException e){
            return ResponseUtil.makeErrorResponse(ResponseEnum.NotLoginError);
        }

        return ResponseUtil.makeSuccessResponse(user);
    }

    @PostMapping("/headImg")
    public ResultVO<Object> uploadHeadImg(@RequestParam(value = "file") MultipartFile file, Model model, HttpServletRequest request) {
        try {
            String fileName = FileUtil.uploadFile(file);
            service.insertUser(fileName);
        }catch (NullPointerException e){
            e.printStackTrace();
            log.error("download file exception [{}]",e.getMessage());
            return ResponseUtil.makeErrorResponse(ResponseEnum.NotLoginError);
        }

        return ResponseUtil.makeSuccessResponse();
    }

    @GetMapping("/headImg")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam("fileName") String fileName) {

        return FileUtil.downloadFile(request, response, fileName);

    }

    @GetMapping("/shakeUser")
    public ResultVO<Object> shake() {
        User user = null;
        try {
            user = service.addAndGetShakeUser();
        } catch (InterruptedException e) {
            log.error("addAndGetShakeUser, sleep error");
            log.error(e.getMessage());
            e.printStackTrace();
            return ResponseUtil.makeErrorResponse(ResponseEnum.Error);
        }
        return ResponseUtil.makeSuccessResponse(user);
    }

    @GetMapping("/findNearby")
    public ResultVO<Object> findNearBy(double longitude, double latitude ) {
        List<User> users = service.findNearBy(longitude, latitude);

        return ResponseUtil.makeSuccessResponse(users);
    }

//    @PostMapping("/VerifyCodeEmail")
//    public ResultVO<Object> getVerificatinCodeByEmail(String email) {
//        String code = service.getVerificationCodeByEmail(email);
//
//        return ResponseUtil.makeSuccessResponse(code);
//    }

    @GetMapping("/VerifyCodeEmail")
    public ResultVO<Object>  getVerificationCodeByEmail(String email){
        String checkCode = String.valueOf(new Random().nextInt(899999) + 100000);
        HostHolder.codes.set(checkCode);

        redisTemplate.opsForValue().set(email,checkCode,60, TimeUnit.SECONDS);

        String message = "your verification code is："+checkCode;
        try {
            service.sendSimpleMail(email, "verification code", message);
        }catch (Exception e){
            return ResponseUtil.makeErrorResponse(ResponseEnum.Error);
        }
        return ResponseUtil.makeSuccessResponse(checkCode);
    }

    @PostMapping("/signupWithEmail")
    public ResultVO<Object> signupWithEmail(
            @RequestParam String username,
            @RequestParam String password,
            HttpServletResponse response,
            String code,
            String email) {
        log.info("--------------get---------");
        log.info("get code"+ redisTemplate.opsForValue().get(email));

        if (code == null || !code.equals(redisTemplate.opsForValue().get(email))){
            return ResponseUtil.makeErrorResponse(ResponseEnum.VerificationError);
        }
        try{
            String token = service.createUser(username, password);
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
        }catch (UsernameExistException e){
            e.printStackTrace();
            return ResponseUtil.makeErrorResponse(ResponseEnum.UserNameExist);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseUtil.makeErrorResponse(ResponseEnum.Error);
        }
        return ResponseUtil.makeSuccessResponse();

    }

}
