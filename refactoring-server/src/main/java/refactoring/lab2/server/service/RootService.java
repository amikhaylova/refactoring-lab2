package refactoring.lab2.server.service;

import org.springframework.stereotype.Service;
import refactoring.lab2.server.entity.Root;
import refactoring.lab2.server.repository.RootRepository;

import java.util.Optional;

@Service
public class RootService {

    private final RootRepository rootRepository;

    public RootService(RootRepository rootRepository) {
        this.rootRepository = rootRepository;
    }

    public Root getByRootAndSaveIfNotExists(String root) {
        Root rootToReturn;
        Optional<Root> rootFromRepo = rootRepository.findByRoot(root);
        if (!rootFromRepo.isPresent()) {
            Root rootToPersist = new Root();
            rootToPersist.setRoot(root);
            rootToReturn = rootRepository.save(rootToPersist);
        } else {
            rootToReturn = rootFromRepo.get();
        }
        return rootToReturn;
    }
}
