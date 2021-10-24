package refactoring.lab2.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import refactoring.lab2.server.entity.Suffix;

import java.util.Optional;

public interface SuffixRepository extends JpaRepository<Suffix, Integer> {
    Optional<Suffix> findBySuffix(String suffix);
}
