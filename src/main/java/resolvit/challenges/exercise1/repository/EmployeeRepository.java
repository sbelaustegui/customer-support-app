package resolvit.challenges.exercise1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import resolvit.challenges.exercise1.model.Employee;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    void deleteAll();

    List<Employee> findAll();
    Optional<Employee> findById(Integer id);

    Optional<Employee> findByType(String type);

    Optional<Employee> findTop1ByOrderByLevelAsc();

}
