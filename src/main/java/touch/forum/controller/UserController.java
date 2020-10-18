package touch.forum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import touch.forum.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService service;

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
}
