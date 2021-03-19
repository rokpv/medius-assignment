package si.medius.repository;

import org.springframework.data.repository.CrudRepository;
import si.medius.entity.Problem;

public interface ProblemRepository extends CrudRepository<Problem, String> {
}
