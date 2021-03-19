package si.medius.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import si.medius.entity.Problem;
import si.medius.entity.Solution;
import si.medius.exception.BadRequestException;
import si.medius.service.ProblemService;
import si.medius.solver.LightsOutSolver;

@RestController
@RequestMapping("/api/problems")
public class ProblemController {

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
    public Problem getProblemById(@PathVariable @NonNull String id) throws BadRequestException {
        try {
            return problemService.getProblemById(id);
        } catch (NotFoundException e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping("/")
    public Problem createProblem(@RequestBody Problem problem) throws BadRequestException {
        int size = problem.getSize();
        String description = problem.getDescription();

        if (size > 8 || size < 3) {
            throw new BadRequestException("Problem size must be between 3 and 8.");
        }

        if (description.length() != size * size) {
            throw new BadRequestException("Description is not of correct dimensions. Expected " + size + "x" + "size.");
        }

        if (!description.matches("^[01]*$")) {
            throw new BadRequestException("Description must contain only the characters 0 and 1.");
        }

        Solution solution = lightsOutSolver.solve(problem);
        if (solution == null) {
            throw new BadRequestException("Given problem is unsolvable.");
        }

        return problemService.createProblem(problem);
    }
}
