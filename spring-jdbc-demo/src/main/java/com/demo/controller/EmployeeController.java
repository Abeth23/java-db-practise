package com.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Employee;
import com.demo.repository.EmployeeRepository;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeRepository repo;

    @PostMapping
    public String addEmployee(@RequestBody Employee e) {
        return repo.insertEmployee(e) + " record inserted!";
    }

    @GetMapping
    public List<Employee> getAll(){
        return repo.getAllEmployees();
    }
}