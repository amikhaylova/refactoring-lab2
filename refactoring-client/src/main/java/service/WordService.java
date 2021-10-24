package service;

import DTO.WordDTO;
import exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
public class WordService {
    private String currentWord;
    private WordDTO wordDTO;
    private Scanner scanner;
    public static final String SAME_ROOT_WORDS_MSG = "Известные однокоренные слова:";
    public static final String EMPTY_STRING_MSG = "Вводимое слово не должно быть пустой строкой";
    public static final String YES = "да";
    public static final String QUIT = "q";


    public WordService() {
        this.scanner = new Scanner(System.in);
        this.wordDTO = new WordDTO();
    }

    public String readNewWord() throws ValidationException {
        currentWord = printMsgAndReadLine("Введите слово: ");
        if (currentWord.trim().equals(""))
            throw new ValidationException(EMPTY_STRING_MSG);
        if (currentWord.toLowerCase().equals(QUIT))
            System.exit(0);
        return currentWord;
    }

    private String readPrefix() {
        return printMsgAndReadLine("Приставка: ");
    }

    private String readRoot() {
        return printMsgAndReadLine("Корень: ");
    }

    private List<String> readSuffixes() {
        List<String> suffixes = new ArrayList<>();
        boolean continueToRead = true;
        while (continueToRead) {
            String suffix = printMsgAndReadLine("Суффикс или окончание: ");
            if (!suffix.trim().equals(""))
                suffixes.add(suffix);
            else
                continueToRead = false;
        }
        return suffixes;
    }

    private String printMsgAndReadLine(String msg) {
        System.out.print(msg);
        return scanner.nextLine();
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void printMsgWordIsAdded(String word) {
        System.out.println("Слово " + word + " добавлено.");
    }

    public WordDTO readWordByParts() {
        wordDTO.setWord(currentWord);
        wordDTO.setPrefix(readPrefix());
        wordDTO.setRoot(readRoot());
        wordDTO.setSuffixes(readSuffixes());
        return wordDTO;
    }

    public void printSameRootWords(WordDTO[] wordDTOS) {
        for (WordDTO s : wordDTOS) {
            System.out.println(s.toString());
        }
    }

}
