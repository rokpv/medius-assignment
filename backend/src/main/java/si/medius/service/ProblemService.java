package si.medius.service;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.medius.entity.Problem;
import si.medius.repository.ProblemRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Service
public class ProblemService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
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

    public Problem createProblem(Problem problem) {
        problemRepository.save(problem);
        return problem;
    }

    /**
     * Prefills some data (since the Db is in-memory) for testing and demo purposes.
     */
    @PostConstruct
    private void prepopulate() {
        Problem p = new Problem(3, "010101010");
        problemRepository.save(p);

        p = new Problem(5, "0000000100010100010000000");
        problemRepository.save(p);

        p = new Problem(8,
                "00000000" +
                        "00000000" +
                        "11111111" +
                        "00000000" +
                        "11111111" +
                        "00000000" +
                        "11111111" +
                        "00000000"
        );
        problemRepository.save(p);

        logger.info("Prepopulated Problem table with 3 entries.");
    }
}
