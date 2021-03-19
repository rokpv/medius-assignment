package si.medius.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "solution")
public class Solution {
    @Id
    @GeneratedValue
    private String id;

    @OneToOne
    private Problem problem;

    @OneToMany(targetEntity = SolutionStep.class)
    private List<SolutionStep> solutionSteps;

    public Solution() { }

    public Solution(String id, Problem problem, List<SolutionStep> solutionSteps) {
        this.id = id;
        this.problem = problem;
        this.solutionSteps = solutionSteps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }
}
