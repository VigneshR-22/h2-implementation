package com.h2.controller;

import com.h2.dto.Employee;
import com.h2.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @GetMapping("/employees")
    @Operation(tags = "Employee", description = "Retrieve all employee details")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",
            content = @Content(schema = @Schema(implementation = Employee.class)))})
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> getEmployees = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(getEmployees);
    }
}
