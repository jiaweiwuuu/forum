package touch.forum.consts;

import lombok.Getter;
import touch.forum.exception.IncorrectPasswordException;

@Getter
public enum ResponseEnum {
    Success(0, "success"),
    Error(-1,"error"),
    UserNameExist(-2, "username exist"),
    UserNotExist(-3, "user not exist"),
    IncorrectPassword(-4, "incorrect password"),
    NotLoginError(-100,"not log in");

    private Integer code;
    private String message;

    ResponseEnum(Integer code, String message ){
        this.code = code;
        this.message = message;
    }


}
