package si.medius.repository;

import org.springframework.data.repository.CrudRepository;
import si.medius.entity.Solution;

public interface SolutionRepository extends CrudRepository<Solution, String> {
}
