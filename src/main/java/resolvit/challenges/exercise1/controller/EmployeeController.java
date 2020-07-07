package resolvit.challenges.exercise1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import resolvit.challenges.exercise1.exception.NotFoundException;
import resolvit.challenges.exercise1.model.Employee;
import resolvit.challenges.exercise1.service.EmployeeService;

import java.util.List;

/**
 * @author Sebastian Belaustegui
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/support")
    public String getSupport() {
        return employeeService.findFirstNotBusy().orElseThrow(() -> new NotFoundException("All busy!"));
    }
}
