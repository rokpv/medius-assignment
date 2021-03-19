package si.medius.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.medius.entity.Problem;
import si.medius.repository.ProblemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Iterable<Problem> getProblems() {
        return problemRepository.findAll();
    }

    public Problem getProblemById(String id) throws NotFoundException {
        Optional<Problem> problem = problemRepository.findById(id);

        if (!problem.isPresent()) {
            throw new NotFoundException("Problem with " + id + " not found.");
        }

        return problem.get();
    }
}
