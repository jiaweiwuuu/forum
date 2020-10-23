package touch.forum.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
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
//        try{
//
//        }catch (Exception e){
//            log.error("download file exception [{}]",e.getMessage());
//            return ResponseUtil.makeErrorResponse(ResponseEnum.Error);
//        }
//        return ResponseUtil.makeSuccessResponse();

    }

//    @GetMapping("/headImg")
//    public String downloadFile(HttpServletRequest request, HttpServletResponse response,
//                               @RequestParam("fileName") String fileName) {
//        if (fileName != null) {
//            File path = null;
//            try {
//                path = new File(ResourceUtils.getURL("classpath:").getPath());
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            String filePath = path.getAbsolutePath()+"/static/upload/";
//
//
//            File file = new File(filePath,fileName );
//            if (file.exists()) {
//                response.setContentType("application/octet-stream");//
//                response.setHeader("content-type", "application/octet-stream");
//                response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
//                byte[] buffer = new byte[1024];
//                FileInputStream fis = null;
//                BufferedInputStream bis = null;
//                try {
//                    fis = new FileInputStream(file);
//                    bis = new BufferedInputStream(fis);
//                    OutputStream os = response.getOutputStream();
//                    int i = bis.read(buffer);
//                    while (i != -1) {
//                        os.write(buffer, 0, i);
//                        i = bis.read(buffer);
//                    }
//                    System.out.println("success");
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    if (bis != null) {
//                        try {
//                            bis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    if (fis != null) {
//                        try {
//                            fis.close();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//        }
//        return null;
//    }

}
