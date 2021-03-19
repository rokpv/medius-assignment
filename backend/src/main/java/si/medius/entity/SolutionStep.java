package si.medius.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SolutionStep {
    public enum SolutionMove {
        ON, OFF
    }

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @ManyToOne
    private Solution solution;

    private SolutionMove move;

    private int stepNumber;

    public SolutionStep() { }

    public SolutionStep(String id, Solution solution, SolutionMove move, int stepNumber) {
        this.id = id;
        this.solution = solution;
        this.move = move;
        this.stepNumber = stepNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Solution getSolution() {
        return solution;
    }

    public void setSolution(Solution solution) {
        this.solution = solution;
    }

    public SolutionMove getMove() {
        return move;
    }

    public void setMove(SolutionMove move) {
        this.move = move;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(int stepNumber) {
        this.stepNumber = stepNumber;
    }
}
