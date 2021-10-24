package refactoring.lab2.server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class WordDTO {
    @NotEmpty(message = "field word should not be empty")
    @Pattern(regexp = "^[A-Za-zА-яа-я]*$",
            message = "should contain only letters")
    @NotNull(message = "word should not be null")
    private String word;
    @Pattern(regexp = "^[A-Za-zА-яа-я]*$",
            message = "should contain only letters")
    private String prefix;
    @NotEmpty(message = "field root should not be empty")
    @Pattern(regexp = "^[A-Za-zА-яа-я]*$",
            message = "should contain only letters")
    @NotNull(message = "root should not be null")
    private String root;
    @Pattern(regexp = "^[A-Za-zА-яа-я]*$",
            message = "should contain only letters")
    private List<String> suffixes;

    public WordDTO(String word, String prefix, String root, List<String> suffixes) {
        this.word = getPartValue(word);
        this.prefix = getPartValue(prefix);
        this.root = getPartValue(root);
        this.suffixes = getListValue(suffixes);
    }

    private List<String> getListValue(List<String> list) {
        return list.stream().map(this::getPartValue).collect(Collectors.toList());
    }

    private String getPartValue(String part) {
        if (part != null && !part.equals(""))
            return part.toLowerCase();
        else
            return null;
    }

    public boolean checkIfWorldIsValid() {
        StringBuilder composedWord = new StringBuilder();
        if (prefix != null)
            composedWord.append(prefix);
        if (root != null)
            composedWord.append(root);
        for (String s : suffixes) {
            if (s != null)
                composedWord.append(s);
        }
        return composedWord.toString().equals(word);
    }

    public void setWord(String word) {
        this.word = getPartValue(word);
    }

    public void setPrefix(String prefix) {
        this.prefix = getPartValue(prefix);
    }

    public void setRoot(String root) {
        this.root = getPartValue(root);
    }

    public void setSuffixes(List<String> suffixes) {
        this.suffixes = getListValue(suffixes);
    }
}
