package resolvit.challenges.exercise1;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import resolvit.challenges.exercise1.model.Employee;
import resolvit.challenges.exercise1.repository.EmployeeRepository;
import resolvit.challenges.exercise1.service.EmployeeService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Exercise1ApplicationTests {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeService employeeService;

    @Before
    public void setUpDb() {
        Employee employee = new Employee("Operador", 2, 3, "Hola soy el operador!");
        employeeRepository.save(employee);
    }

    @Test
    void shouldCreateEmployee() {
        employeeRepository.deleteAll();
        Employee employee = new Employee("Nuevo Empleado", 2, 3, "Hola soy el nuevo empleado!");
        Employee savedEmployee = employeeService.saveEmployee(employee);
        assertEquals(employee.getType(), savedEmployee.getType());
        assertEquals(employee.getQuantity(), savedEmployee.getQuantity());
        assertEquals(employee.getGreetings(), savedEmployee.getGreetings());
        assertEquals(employee.getLevel(), savedEmployee.getLevel());
    }

    @Test
    void shouldUpdateQuantity() {
        Employee employee = employeeRepository.findAll().get(0);
        Optional<Employee> savedEmployee = employeeService.updateEmployeeQuantity(employee.getType(), employee.getQuantity() + 5);
        assertEquals(employee.getQuantity() + 5, savedEmployee.orElseThrow().getQuantity());
    }

}
