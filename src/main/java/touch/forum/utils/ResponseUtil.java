package touch.forum.utils;

import touch.forum.ResultVO;
import touch.forum.consts.ResponseEnum;

public class ResponseUtil {
    public static ResultVO<Object> makeSuccessResponse(Object object){
        ResultVO<Object> response = new ResultVO<>();
        response.setCode(ResponseEnum.Success.getCode());
        response.setMsg(ResponseEnum.Success.getMessage());
        response.setData(object);
        return response;
    }

    public static ResultVO<Object> makeSuccessResponse(){
        return makeSuccessResponse(null);
    }

    public static ResultVO<Object> makeErrorResponse(ResponseEnum responseEnum){
        ResultVO<Object> response = new ResultVO<>();
        response.setCode(responseEnum.getCode());
        response.setMsg(responseEnum.getMessage());
        return response;
    }
}
