package refactoring.lab2.server.exception;

public class WordAlreadyExists extends RuntimeException {
    public WordAlreadyExists(String message) {
        super(message);
    }
}
