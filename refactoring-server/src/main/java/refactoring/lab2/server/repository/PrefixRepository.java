package refactoring.lab2.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import refactoring.lab2.server.entity.Prefix;

import java.util.Optional;

@Repository
public interface PrefixRepository extends JpaRepository<Prefix, Integer> {
    Optional<Prefix> findByPrefix(String prefix);
}
