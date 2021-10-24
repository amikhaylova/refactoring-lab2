package service;

import DTO.WordDTO;
import errorHandler.RestTemplateResponseErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RequestService {

    private RestTemplate restTemplate;
    private final String serverResourceUrl = "http://localhost:8080/words";
    public static final String SERVER_IS_NOT_AVAILABLE_MSG = "Сервер недоступен";

    public RequestService() {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public WordDTO[] getSameRootWords(String word) {
        ResponseEntity<WordDTO[]> response
                = restTemplate.getForEntity(serverResourceUrl + "/" + word, WordDTO[].class);
        return response.getBody();
    }

    public void saveNewWord(WordDTO wordDTO) {
        restTemplate.postForEntity(serverResourceUrl, wordDTO, String.class);
    }


}
