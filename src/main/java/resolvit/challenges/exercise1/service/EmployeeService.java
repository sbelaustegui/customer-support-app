package resolvit.challenges.exercise1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import resolvit.challenges.exercise1.model.Employee;
import resolvit.challenges.exercise1.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Sebastian Belaustegui
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(int id) {
        return employeeRepository.findById(id);
    }

    public Optional<String> findFirstNotBusy() {
        return employeeRepository.findTop1ByOrderByLevelAsc().flatMap(Employee::getGreetings);
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Optional<Employee> updateEmployeeQuantity(String type, int quantity) {
        Optional<Employee> optionalEmployee = employeeRepository.findByType(type);
        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();
            employee.setQuantity(quantity);
            return Optional.of(saveEmployee(employee));
        }
        return Optional.empty();
    }
}
