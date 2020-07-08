package resolvit.challenges.exercise1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import resolvit.challenges.exercise1.exception.NotFoundException;
import resolvit.challenges.exercise1.model.Employee;
import resolvit.challenges.exercise1.payload.SupportResponse;
import resolvit.challenges.exercise1.service.EmployeeService;

import java.util.List;
import java.util.Optional;

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
    public SupportResponse getSupport() {
        return employeeService.findFirstNotBusy()
                .map(msg -> new SupportResponse(msg, false))
                .orElseGet(() -> new SupportResponse("All busy!", true));
    }
}
