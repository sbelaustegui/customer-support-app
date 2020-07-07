package resolvit.challenges.exercise1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import resolvit.challenges.exercise1.model.Employee;
import resolvit.challenges.exercise1.repository.EmployeeRepository;

@Component
public class StartUpDb implements ApplicationRunner {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public StartUpDb(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            Employee operator = new Employee("Operador", 1, 2, "Hola, soy el operador en que puedo ayudarle.");
            Employee supervisor = new Employee("Supervisor", 2, 2, "Hola, soy el supervisor en que puedo ayudarle.");
            Employee manager = new Employee("Gerente", 3, 2, "Hola, soy el gerente en que puedo ayudarle.");

            supervisor.setNextEmployee(manager);
            operator.setNextEmployee(supervisor);

            employeeRepository.save(manager);
            employeeRepository.save(supervisor);
            employeeRepository.save(operator);

        } catch (Exception ignored) {}
    }
}
