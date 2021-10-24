package refactoring.lab2.server.service;

import org.springframework.stereotype.Service;
import refactoring.lab2.server.entity.Root;
import refactoring.lab2.server.entity.Word;
import refactoring.lab2.server.exception.WordAlreadyExists;
import refactoring.lab2.server.exception.WordNotFoundException;
import refactoring.lab2.server.repository.WordRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {
    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> getTheSameRootWords(String wordToFind) {
        Word word = getWordByFullWord(wordToFind);
        Root root = word.getRoot();
        return wordRepository.findAllByRootOrderByPartsNumber(root);
    }

    private Word getWordByFullWord(String word) {
        return wordRepository.findByWord(word)
                .orElseThrow(() -> new WordNotFoundException(word + " is not found"));
    }

    public void saveNewWord(Word word) {
        Optional<Word> wordFromRepo = wordRepository.findByWord(word.getWord());
        if (wordFromRepo.isPresent())
            throw new WordAlreadyExists(word.getWord() + " already exists");
        else
            wordRepository.save(word);
    }
}
