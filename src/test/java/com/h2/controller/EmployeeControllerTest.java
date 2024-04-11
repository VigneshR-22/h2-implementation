package com.h2.controller;

import com.h2.dto.Employee;
import com.h2.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeControllerTest {

    @Mock
    EmployeeRepository repository;

    @InjectMocks
    EmployeeController controller;

    @Test
    public void unitTest_getEmployees() {

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Rick");
        employee.setEmail("rick@emp.com");
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        when(repository.findAll()).thenReturn(employeeList);
        ResponseEntity<?> actualResponse = controller.getEmployees();
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(employeeList, actualResponse.getBody());

    }

    @Test
    public void unitTest_addEmployee() {

        Employee employee = new Employee();
        employee.setName("Rick");
        employee.setEmail("rick@emp.com");
        when(repository.save(any(Employee.class))).thenReturn(employee);
        ResponseEntity<?> actualResponse = controller.addEmployee(employee);
        assertEquals(HttpStatus.CREATED, actualResponse.getStatusCode());

    }

    @Test
    public void unitTest_updateEmployee() {

        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("Ricky");
        employee.setEmail("ricky@emp.com");
        when(repository.existsById(employee.getId())).thenReturn(true);
        when(repository.save(employee)).thenReturn(employee);
        ResponseEntity<?> actualResponse = controller.updateEmployee(employee);
        assertEquals(HttpStatus.ACCEPTED, actualResponse.getStatusCode());

    }

    @Test
    public void unitTest_removeEmployee() {

        Integer id = 1;
        when(repository.existsById(id)).thenReturn(true);
        doNothing().when(repository).deleteById(id);
        ResponseEntity<?> actualResponse = controller.removeEmployee(id);
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());

    }

}
