package touch.forum.exception;

public class UsernameExistException extends Exception{

    public UsernameExistException() {
    }

    public UsernameExistException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
