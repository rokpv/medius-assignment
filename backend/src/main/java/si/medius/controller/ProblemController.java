package si.medius.controller;

import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import si.medius.entity.Problem;
import si.medius.entity.Solution;
import si.medius.service.ProblemService;
import si.medius.solver.LightsOutSolver;

import javax.annotation.PostConstruct;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/problems")
public class ProblemController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ProblemService problemService;
    private final LightsOutSolver lightsOutSolver;

    @Autowired
    public ProblemController(ProblemService problemService, LightsOutSolver lightsOutSolver) {
        this.problemService = problemService;
        this.lightsOutSolver = lightsOutSolver;
    }

    @GetMapping("/")
    public Iterable<Problem> getProblems() {
        return problemService.getProblems();
    }

    @GetMapping("/{id}")
    public Problem getProblemById(@PathVariable @NonNull String id) throws HttpClientErrorException {
        try {
            return problemService.getProblemById(id);
        } catch (NotFoundException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/")
    public Problem createProblem(@RequestBody Problem problem) throws HttpClientErrorException {
        int size = problem.getSize();
        String description = problem.getDescription();

        if (size > 8 || size < 3) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Problem size must be between 3 and 8.");
        }

        if (description.length() != size * size) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Description is not of correct dimensions. Expected " + size + "x" + "size.");
        }

        if (!description.matches("^[01]*$")) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Description must contain only the characters 0 and 1.");
        }

        Solution solution = lightsOutSolver.solve(problem);
        if (solution == null) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Given problem is unsolvable.");
        }

        return problemService.createProblem(problem, solution);
    }

    /**
     * Prefills some data (since the Db is in-memory) for testing and demo purposes.
     */
    @PostConstruct
    private void prepopulate() {
        Problem p = new Problem(3, "010101010");
        Solution s = lightsOutSolver.solve(p);
        problemService.createProblem(p, s);

        p = new Problem(5, "0000000100010100010000000");
        s = lightsOutSolver.solve(p);
        problemService.createProblem(p, s);

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
        s = lightsOutSolver.solve(p);
        problemService.createProblem(p, s);

        logger.info("Prepopulated Problem table with 3 entries.");
    }
}
