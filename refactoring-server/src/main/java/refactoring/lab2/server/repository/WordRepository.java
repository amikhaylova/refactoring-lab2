package refactoring.lab2.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import refactoring.lab2.server.entity.Root;
import refactoring.lab2.server.entity.Word;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {
    List<Word> findAllByRootOrderByPartsNumber(Root root);

    Optional<Word> findByWord(String word);

}
