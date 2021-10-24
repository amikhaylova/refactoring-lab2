package refactoring.lab2.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import refactoring.lab2.server.entity.Root;

import java.util.Optional;

@Repository
public interface RootRepository extends JpaRepository<Root, Integer> {
    Optional<Root> findByRoot(String root);
}
