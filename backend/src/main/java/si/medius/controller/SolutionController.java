package si.medius.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import si.medius.entity.Solution;
import si.medius.service.SolutionService;

import java.util.ArrayList;
import java.util.List;

@RestController("/api/solutions")
public class SolutionController {

    private final SolutionService solutionService;

    @Autowired
    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @GetMapping("/")
    public List<Solution> getSolutions() {
        return new ArrayList<>();
    }

    @GetMapping("/{id}")
    public Solution getSolutionById(@PathVariable String id) {
        return null;
    }
}
