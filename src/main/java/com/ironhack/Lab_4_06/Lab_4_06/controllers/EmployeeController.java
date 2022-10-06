package com.ironhack.Lab_4_06.Lab_4_06.controllers;


import com.ironhack.Lab_4_06.Lab_4_06.dto.EmployeeDto;
import com.ironhack.Lab_4_06.Lab_4_06.models.Employee;
import com.ironhack.Lab_4_06.Lab_4_06.models.EmployeeStatus;
import com.ironhack.Lab_4_06.Lab_4_06.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee findById(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @GetMapping("/status/{status}")
    public List<Employee> getByStatus(@PathVariable EmployeeStatus status) {
        return employeeService.findByStatus(status);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody EmployeeDto employee) {

        employeeService.createEmployee(employee);

    }

    @PatchMapping("/{employeeId}/status")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateStatus(@PathVariable(name = "employeeId") Long employeeId, @RequestParam EmployeeStatus status) {
        return employeeService.updateEmployeeStatus(employeeId, status);

    }

    @PatchMapping("/{employeeId}/department")
    @ResponseStatus(HttpStatus.OK)
    public Employee updateDepartment(@PathVariable(name = "employeeId") Long employeeId, @RequestParam String department) {
        return employeeService.updateEmployeeDepartment(employeeId, department);

    }


}
