package refactoring.lab2.server.service;

import org.springframework.stereotype.Service;
import refactoring.lab2.server.entity.Prefix;
import refactoring.lab2.server.repository.PrefixRepository;

import java.util.Optional;

@Service
public class PrefixService {
    private final PrefixRepository prefixRepository;

    public PrefixService(PrefixRepository prefixRepository) {
        this.prefixRepository = prefixRepository;
    }

    public Prefix getByPrefixAndSaveIfNotExists(String prefix) {
        Prefix prefixToReturn;
        Optional<Prefix> prefixFromRepo = prefixRepository.findByPrefix(prefix);
        if (!prefixFromRepo.isPresent()) {
            Prefix prefixToPersist = new Prefix();
            prefixToPersist.setPrefix(prefix);
            prefixToReturn = prefixRepository.save(prefixToPersist);
        } else {
            prefixToReturn = prefixFromRepo.get();
        }
        return prefixToReturn;
    }
}
