package resolvit.challenges.exercise1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import resolvit.challenges.exercise1.model.Employee;
import resolvit.challenges.exercise1.payload.request.EmployeeQuantityUpdate;
import resolvit.challenges.exercise1.payload.response.SupportResponse;
import resolvit.challenges.exercise1.service.EmployeeService;

import javax.validation.Valid;
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

    @PutMapping("/quantity")
    public ResponseEntity updateQuantity(@Valid @RequestBody List<EmployeeQuantityUpdate> employeeQuantities) {
        for (EmployeeQuantityUpdate employeeQuantity : employeeQuantities) {
            Optional<Employee> employee = employeeService.updateEmployeeQuantity(employeeQuantity.getType(), employeeQuantity.getQuantity());
            if(employee.isEmpty()) return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}
