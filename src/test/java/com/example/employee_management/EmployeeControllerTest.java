package com.example.employee_management;

import com.example.employee_management.controller.EmployeeController;
import com.example.employee_management.model.Employee;
import com.example.employee_management.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetEmployeesById_Success() {
        Long employeeId = 1L;
        Employee mockEmployee = new Employee();
        mockEmployee.setId(employeeId);
        mockEmployee.setEmail("test@gmail.com");
        mockEmployee.setName("test");
        mockEmployee.setPosition("lead");
        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.of(mockEmployee));

        ResponseEntity<?> response = employeeController.getEmployees(employeeId, null);

        assertEquals(ResponseEntity.ok(mockEmployee), response);
        verify(employeeService, times(1)).getEmployeeById(employeeId);
        verifyNoMoreInteractions(employeeService);
    }

   @Test
    void testGetEmployeesById_NotFound() {
        Long employeeId = 2L;
        when(employeeService.getEmployeeById(employeeId)).thenReturn(Optional.empty());

        ResponseEntity<?> response = employeeController.getEmployees(employeeId, null);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(employeeService, times(1)).getEmployeeById(employeeId);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    void testGetEmployeesByName_Success() {
        String name = "test";
        Employee mockEmployee1=new Employee();
        mockEmployee1.setId(1L);
        mockEmployee1.setEmail("test1@gmail.com");
        mockEmployee1.setName("test 1");
        mockEmployee1.setPosition("lead");
        Employee mockEmployee2=new Employee();
        mockEmployee1.setId(2L);
        mockEmployee1.setEmail("test2@gmail.com");
        mockEmployee1.setName("test 2");
        mockEmployee1.setPosition("lead");
        List<Employee> mockEmployees = List.of(
                mockEmployee1,mockEmployee2
        );
        when(employeeService.getEmployeesByName(name)).thenReturn(mockEmployees);

        ResponseEntity<?> response = employeeController.getEmployees(null, name);

        assertEquals(ResponseEntity.ok(mockEmployees), response);
        verify(employeeService, times(1)).getEmployeesByName(name);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    void testGetEmployeesByName_NotFound() {
        String name = "Unknown";
        when(employeeService.getEmployeesByName(name)).thenReturn(Collections.emptyList());

        ResponseEntity<?> response = employeeController.getEmployees(null, name);

        assertEquals(ResponseEntity.notFound().build(), response);
        verify(employeeService, times(1)).getEmployeesByName(name);
        verifyNoMoreInteractions(employeeService);
    }

    @Test
    void testGetAllEmployees() {
        Employee mockEmployee1=new Employee();
        mockEmployee1.setId(1L);
        mockEmployee1.setEmail("test1@gmail.com");
        mockEmployee1.setName("test 1");
        mockEmployee1.setPosition("lead");
        Employee mockEmployee2=new Employee();
        mockEmployee1.setId(2L);
        mockEmployee1.setEmail("test2@gmail.com");
        mockEmployee1.setName("test 2");
        mockEmployee1.setPosition("lead");
        List<Employee> mockEmployees = List.of(
                mockEmployee1,mockEmployee2
        );
        when(employeeService.getAllEmployees()).thenReturn(mockEmployees);

        ResponseEntity<?> response = employeeController.getEmployees(null, null);

        assertEquals(ResponseEntity.ok(mockEmployees), response);
        verify(employeeService, times(1)).getAllEmployees();
        verifyNoMoreInteractions(employeeService);
    }
}
