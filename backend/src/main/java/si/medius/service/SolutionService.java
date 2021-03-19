package si.medius.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.medius.repository.SolutionRepository;

@Service
public class SolutionService {

    private final SolutionRepository solutionRepository;

    @Autowired
    public SolutionService(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }
}
