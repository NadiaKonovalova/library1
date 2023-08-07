package exceptions;

public class UserCreationCancelledException extends Throwable {
    public UserCreationCancelledException(String message) {
        super(message);
    }
}
