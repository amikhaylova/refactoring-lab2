import DTO.WordDTO;
import exception.*;
import org.springframework.web.client.ResourceAccessException;
import service.RequestService;
import service.WordService;

import static service.RequestService.SERVER_IS_NOT_AVAILABLE_MSG;
import static service.WordService.SAME_ROOT_WORDS_MSG;
import static service.WordService.YES;

public class Client {
    public static void main(String[] args) {
        while (true) {
            RequestService requestService = new RequestService();
            WordService wordService = new WordService();
            String newWord;
            try {
                newWord = wordService.readNewWord();
            } catch (ValidationException ex) {
                System.out.println(ex.getMessage());
                continue;
            }
            try {
                WordDTO[] dtos = requestService.getSameRootWords(newWord);
                System.out.println(SAME_ROOT_WORDS_MSG);
                wordService.printSameRootWords(dtos);
            } catch (ValidationException | ServerException ex) {
                System.out.println(ex.getMessage());
            } catch (ResourceAccessException ex) {
                System.out.println(SERVER_IS_NOT_AVAILABLE_MSG);
            } catch (WordNotFoundException ex) {
                System.out.println(ex.getMessage());
                if (wordService.readLine().toLowerCase().equals(YES)) {
                    WordDTO wordDTO = wordService.readWordByParts();
                    try {
                        requestService.saveNewWord(wordDTO);
                        wordService.printMsgWordIsAdded(wordDTO.toString());
                    } catch (ValidationException | ServerException | ComposedWordIsNotValid | WordAlreadyExists exp) {
                        System.out.println(exp.getMessage());
                    } catch (ResourceAccessException exp) {
                        System.out.println(SERVER_IS_NOT_AVAILABLE_MSG);
                    }
                }
            }

        }
    }
}
