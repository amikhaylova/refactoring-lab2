package exception;

public class ComposedWordIsNotValid extends RuntimeException {
    public ComposedWordIsNotValid(String message) {
        super(message);
    }
}
