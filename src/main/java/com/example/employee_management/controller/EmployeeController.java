package com.example.employee_management.controller;

import com.example.employee_management.model.Employee;
import com.example.employee_management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    // Ping Endpoint
    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    // Get All Employees or by ID/Name
    @GetMapping
    public ResponseEntity<?> getEmployees(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name) {
        if (id != null) {
            return service.getEmployeeById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } else if (name != null) {
            List<Employee> employees = service.getEmployeesByName(name); // Assuming this returns a list
            if (!employees.isEmpty()) {
                return ResponseEntity.ok(employees);
            }
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.getAllEmployees());
    }

    // Create a New Employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    // Update Employee by ID
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        try {
            return ResponseEntity.ok(service.updateEmployee(id, employeeDetails));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        service.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
}
