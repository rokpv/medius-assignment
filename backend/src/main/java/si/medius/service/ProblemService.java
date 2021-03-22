package si.medius.service;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.medius.entity.Problem;
import si.medius.entity.Solution;
import si.medius.repository.ProblemRepository;
import si.medius.repository.SolutionRepository;

import java.util.Optional;

@Service
public class ProblemService {

    private final ProblemRepository problemRepository;
    private final SolutionRepository solutionRepository;


    @Autowired
    public ProblemService(ProblemRepository problemRepository, SolutionRepository solutionRepository) {
        this.problemRepository = problemRepository;
        this.solutionRepository = solutionRepository;
    }

    public Iterable<Problem> getProblems() {
        return problemRepository.findByOrderBySizeAsc();
    }

    public Problem getProblemById(String id) throws NotFoundException {
        Optional<Problem> problem = problemRepository.findById(id);

        if (!problem.isPresent()) {
            throw new NotFoundException("Problem with " + id + " not found.");
        }

        return problem.get();
    }

    public Problem createProblem(Problem problem, Solution solution) {
        problemRepository.save(problem);
        solutionRepository.save(solution);
        return problem;
    }
}
