package si.medius.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
public class Solution {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @OneToOne
    private Problem problem;

    @OneToMany(targetEntity = SolutionStep.class)
    private List<SolutionStep> steps;

    public Solution() { }

    public Solution(String id, Problem problem, List<SolutionStep> steps) {
        this.id = id;
        this.problem = problem;
        this.steps = steps;
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
