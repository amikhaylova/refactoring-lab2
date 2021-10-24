package exception;

public class WordAlreadyExists extends RuntimeException {
    public WordAlreadyExists(String message) {
        super(message);
    }
}
