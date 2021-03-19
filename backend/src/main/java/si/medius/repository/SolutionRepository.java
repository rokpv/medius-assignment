package si.medius.repository;

import org.springframework.data.repository.CrudRepository;
import si.medius.entity.Solution;

import java.util.Optional;

public interface SolutionRepository extends CrudRepository<Solution, String> {
    Optional<Solution> findSolutionByProblemId(String problemId);
}
