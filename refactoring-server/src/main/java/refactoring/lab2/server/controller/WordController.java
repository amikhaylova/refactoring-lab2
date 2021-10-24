package refactoring.lab2.server.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import refactoring.lab2.server.dto.WordDTO;
import refactoring.lab2.server.entity.Word;
import refactoring.lab2.server.mapper.WordMapper;
import refactoring.lab2.server.service.WordService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@CrossOrigin
@RestController

@RequestMapping("/words")
@Validated
public class WordController {
    private final WordService wordService;
    private final WordMapper wordMapper;

    public WordController(WordService wordService, WordMapper wordMapper) {
        this.wordService = wordService;
        this.wordMapper = wordMapper;
    }

    @GetMapping("/{word}")
    public List<WordDTO> getSameRootWords(@PathVariable("word")
                                          @NotNull(message = "word should not be null")
                                          @NotEmpty(message = "word should not be empty")
                                          @Pattern(regexp = "^[A-Za-zА-яа-я]*$", message = "word should contain only letters")
                                                  String word) {
        List<Word> words = wordService.getTheSameRootWords(word);
        return wordMapper.mapWordListToWordDTOList(words);
    }

    @PostMapping
    public ResponseEntity<?> saveNewWord(@RequestBody WordDTO wordDTO) {
        System.out.println("wordDTO " + wordDTO.getWord());
        Word word = wordMapper.mapWordDTOToWord(wordDTO);
        wordService.saveNewWord(word);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
