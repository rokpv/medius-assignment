package si.medius.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.medius.entity.Problem;
import si.medius.repository.ProblemRepository;

import java.util.Optional;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;

    @Autowired
    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Iterable<Problem> getProblems() {
        prepopulate();
        return problemRepository.findAll();
    }

    public Problem getProblemById(String id) throws NotFoundException {
        Optional<Problem> problem = problemRepository.findById(id);

        if (!problem.isPresent()) {
            throw new NotFoundException("Problem with " + id + " not found.");
        }

        return problem.get();
    }

    public Problem createProblem(Problem problem) {
        problemRepository.save(problem);
        return problem;
    }

    private void prepopulate() {
        Problem p1 = new Problem();
        p1.setSize(3);
        p1.setDescription("010101010");
        problemRepository.save(p1);
    }
}
