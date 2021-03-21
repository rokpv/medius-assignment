package si.medius.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import si.medius.entity.Solution;
import si.medius.service.SolutionService;

@RestController
@CrossOrigin("http://localhost:4200")
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
    public Solution getSolutionByProblemId(@PathVariable String id) throws NotFoundException {
        return solutionService.getSolutionByProblemId(id);
    }
}
