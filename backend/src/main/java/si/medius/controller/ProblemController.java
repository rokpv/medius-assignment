package si.medius.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import si.medius.entity.Problem;
import si.medius.service.ProblemService;

@RestController("/api/problems")
public class ProblemController {

    private final ProblemService problemService;

    @Autowired
    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping("/")
    public Iterable<Problem> getProblems() {
        return problemService.getProblems();
    }

    @GetMapping("/{id}")
    public Problem getProblemById(@PathVariable String id) throws NotFoundException {
        return problemService.getProblemById(id);
    }

    @PostMapping("/")
    public Problem createProblem() {
        return null;
    }
}
