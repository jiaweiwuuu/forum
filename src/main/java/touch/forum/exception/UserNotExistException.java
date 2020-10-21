package touch.forum.exception;

public class UserNotExistException extends Exception{
    String message = "Uer not exist";

    @Override
    public String getMessage() {
        return message;
    }
}
