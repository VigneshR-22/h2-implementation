package com.h2.config;

import com.h2.dto.Employee;
import com.h2.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final EmployeeRepository repository;

    @Autowired
    public DataLoader(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository.save(new Employee("Sam","sam@emp.com"));
        repository.save(new Employee("Jack","jack@emp.com"));
        repository.save(new Employee("Dean","dean@emp.com"));
        repository.save(new Employee("Allen","allen@emp.com"));
        repository.save(new Employee("Brock","brock@emp.com"));
    }
}
