package si.medius.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.medius.entity.Problem;
import si.medius.entity.Solution;
import si.medius.repository.SolutionRepository;

import java.util.Optional;

@Service
public class SolutionService {

    private final SolutionRepository solutionRepository;

    @Autowired
    public SolutionService(SolutionRepository solutionRepository) {
        this.solutionRepository = solutionRepository;
    }

    public Iterable<Solution> getSolutions() {
        return solutionRepository.findAll();
    }

    public Solution getSolutionByProblemId(String id) throws NotFoundException {
        Optional<Solution> solution = solutionRepository.findSolutionByProblemId(id);

        if (!solution.isPresent()) {
            throw new NotFoundException("Solution with " + id + " not found.");
        }

        return solution.get();
    }
}
