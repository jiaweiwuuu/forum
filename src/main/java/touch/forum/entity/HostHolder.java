package touch.forum.entity;

import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    private static ThreadLocal<User> users = new ThreadLocal<>();

    public static ThreadLocal<String> codes = new ThreadLocal<>();

    public String getCode(){
        return codes.get();
    }
    public void setCode(String code){
        codes.set(code);
    }
    public void clearCodes(){
        codes.remove();
    }

    public User getUser(){
        return users.get();
    }
    public void setUser(User user){
        users.set(user);
    }
    public void clear(){
        users.remove();
    }
}
