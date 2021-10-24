package refactoring.lab2.server.service;

import org.springframework.stereotype.Service;
import refactoring.lab2.server.entity.Suffix;
import refactoring.lab2.server.repository.SuffixRepository;

import java.util.Optional;

@Service
public class SuffixService {
    private final SuffixRepository suffixRepository;

    public SuffixService(SuffixRepository suffixRepository) {
        this.suffixRepository = suffixRepository;
    }

    public Suffix getBySuffixAndSaveIfNotExists(String suffix) {
        Suffix suffixToReturn;
        Optional<Suffix> suffixFromRepo = suffixRepository.findBySuffix(suffix);
        if (!suffixFromRepo.isPresent()) {
            Suffix suffixToPersist = new Suffix();
            suffixToPersist.setSuffix(suffix);
            suffixToReturn = suffixRepository.save(suffixToPersist);
        } else {
            suffixToReturn = suffixFromRepo.get();
        }
        return suffixToReturn;
    }
}
