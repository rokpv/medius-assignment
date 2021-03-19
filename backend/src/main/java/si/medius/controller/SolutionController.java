package si.medius.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import si.medius.entity.Solution;
import si.medius.service.SolutionService;

@RestController
@RequestMapping("/api/solutions")
public class SolutionController {

    private final SolutionService solutionService;

    @Autowired
    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @GetMapping("/")
    public Iterable<Solution> getSolutions() {
        return solutionService.getSolutions();
    }

    @GetMapping("/problem/{id}")
    public Solution getSolutionById(@PathVariable String id) throws NotFoundException {
        return solutionService.getSolutionByProblemId(id);
    }
}
