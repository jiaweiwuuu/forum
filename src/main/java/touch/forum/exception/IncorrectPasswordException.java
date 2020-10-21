package touch.forum.exception;

public class IncorrectPasswordException extends Exception{

    String message =" incorrect password";
    public IncorrectPasswordException() {
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }
}
