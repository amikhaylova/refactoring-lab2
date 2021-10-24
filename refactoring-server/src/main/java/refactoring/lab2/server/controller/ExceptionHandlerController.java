package refactoring.lab2.server.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import refactoring.lab2.server.exception.ComposedWordIsNotValid;
import refactoring.lab2.server.exception.WordAlreadyExists;
import refactoring.lab2.server.exception.WordNotFoundException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(ComposedWordIsNotValid.class)
    public String handleComposedWordIsNotValidException(ComposedWordIsNotValid ex, HttpServletResponse response) {
        response.setStatus(435);
        return ex.getMessage();
    }

    @ExceptionHandler(WordAlreadyExists.class)
    public String handleWordAlreadyExistsException(WordAlreadyExists ex, HttpServletResponse response) {
        response.setStatus(436);
        return ex.getMessage();
    }

    @ExceptionHandler(WordNotFoundException.class)
    public String handleWordNotFoundExceptionException(WordNotFoundException ex, HttpServletResponse response) {
        response.setStatus(404);
        return ex.getMessage();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleWordNotFoundExceptionException(ConstraintViolationException ex, HttpServletResponse response) {
        response.setStatus(400);
        return ex.getMessage();
    }
}
