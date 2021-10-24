package refactoring.lab2.server.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String word;
    @ManyToOne
    private Prefix prefix;
    @ManyToOne
    private Root root;
    @ManyToMany
    @JoinTable(name = "words_suffixes",
            joinColumns = {@JoinColumn(name = "word_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "suffix", referencedColumnName = "suffix")})
    protected List<Suffix> suffixes;
    @Column
    private Integer partsNumber;

    public Word(String word, Prefix prefix, Root root, List<Suffix> suffixes) {
        this.word = word;
        this.prefix = prefix;
        this.root = root;
        this.suffixes = suffixes;
    }
}
