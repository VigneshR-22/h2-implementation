package com.h2.controller;

import com.h2.dto.Employee;
import com.h2.repository.EmployeeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @GetMapping("/employees")
    @Operation(tags = "Employee", description = "Retrieve all employee details")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<List<Employee>> getEmployees() {
        List<Employee> getEmployees = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(getEmployees);
    }

    @PostMapping("/employee")
    @Operation(tags = "Employee", description = "Add a new Employee")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<?> addEmployees(
            @Parameter
            @RequestBody Employee request
    ) {
        try {
            repository.save(request);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/employee")
    @Operation(tags = "Employee", description = "Update an existing Employee")
    @ApiResponses(value = {@ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<?> updateEmployee(
            @Parameter
            @RequestBody Employee request
    ) {
        if (repository.existsById(request.getId())) {
            try {
                repository.save(request);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/employees")
    @Operation(tags = "Employee", description = "Retrieve all employee details")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = Employee.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")})
    public ResponseEntity<?> removeEmployee(
            @Parameter
            @RequestParam Integer id
    ) {
        if (repository.existsById(id)) {
            try {
                repository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
