package touch.forum.consts;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    Success(0, "success"),Error(-1,"error");
    private Integer code;
    private String message;

    ResponseEnum(Integer code, String message ){
        this.code = code;
        this.message = message;
    }


}
