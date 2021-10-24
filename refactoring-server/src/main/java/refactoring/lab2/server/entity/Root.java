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
public class Root {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String root;
    @OneToMany(mappedBy = "root")
    private List<Word> words;
}
