package si.medius.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import si.medius.solver.LightsOutSolver;

@Service
public class SolverService {
    private final LightsOutSolver lightsOutSolver;

    @Autowired
    public SolverService(LightsOutSolver lightsOutSolver) {
        this.lightsOutSolver = lightsOutSolver;
    }
}
