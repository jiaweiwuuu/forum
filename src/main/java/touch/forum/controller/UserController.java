package touch.forum.controller;

import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import touch.forum.ResultVO;
import touch.forum.consts.ResponseEnum;
import touch.forum.entity.HostHolder;
import touch.forum.entity.Question;
import touch.forum.entity.User;
import touch.forum.service.UserService;
import touch.forum.utils.ResponseUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private HostHolder hostHolder;

    @PostMapping("/signup")
    public String createUser(@RequestParam String username, @RequestParam String password, HttpServletResponse response) {
        String token = service.createUser(username, password);
        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);
        return "success";
    }

    @PostMapping("/signin")
    public String userLogin(@RequestParam String username,
                            @RequestParam String password,
                            @RequestParam(value = "rememberme", defaultValue = "false") boolean rememberMe,
                            HttpServletResponse response) {

        try {
            String token = service.login(username, password, rememberMe);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "login success";
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
}
