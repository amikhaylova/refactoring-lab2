package errorHandler;

import exception.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    public static final String VALIDATION_MSG = "Произошла ошибка валидации. Обратите вниманиие, что слово и его составляющие могут содержать только буквы.";
    public static final String WORD_NOT_FOUND_MSG = "Неизвестное слово. Хотите добавить его в словарь (да/нет)?";
    public static final String COMPOSED_WORD_MSG = "При введении составных частей слова была допущена ошибка.";
    public static final String WORD_EXISTS_MSG = "Данное слово уже существует в словаре";
    public static final String SERVER_EXCEPTION_MSG = "На сервере произошла непредвиденная ошибка";

    @Override
    public boolean hasError(ClientHttpResponse httpResponse)
            throws IOException {
        return (httpResponse.getRawStatusCode() != 200);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse)
            throws IOException {

        int statusCode = httpResponse.getRawStatusCode();

        switch (statusCode) {
            case 404:
                throw new WordNotFoundException(WORD_NOT_FOUND_MSG);
            case 435:
                throw new ComposedWordIsNotValid(COMPOSED_WORD_MSG);
            case 436:
                throw new WordAlreadyExists(WORD_EXISTS_MSG);
            case 400:
                throw new ValidationException(VALIDATION_MSG);
            default:
                throw new ServerException(SERVER_EXCEPTION_MSG);
        }
    }
}
