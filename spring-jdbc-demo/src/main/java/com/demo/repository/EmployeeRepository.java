package com.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.model.Employee;

@Repository
public class EmployeeRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int insertEmployee(Employee e){
        String sql = "INSERT INTO employee (name, department) VALUES (?, ?)";
        return jdbcTemplate.update(sql, e.getName(), e.getDepartment());
    }
    public List<Employee> getAllEmployees(){
        String sql="SELECT * FROM employee";
        return jdbcTemplate.query(sql, (rs, rowNum)->
        new Employee(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("department")
        )
        );
    }
}
