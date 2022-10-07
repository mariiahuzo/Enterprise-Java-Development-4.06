package com.ironhack.Lab_4_06.Lab_4_06.controllers;


import com.ironhack.Lab_4_06.Lab_4_06.dto.PatientDto;
import com.ironhack.Lab_4_06.Lab_4_06.models.EmployeeStatus;
import com.ironhack.Lab_4_06.Lab_4_06.models.Patient;
import com.ironhack.Lab_4_06.Lab_4_06.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {


    private final PatientService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findAll() {

        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient findById(@PathVariable Long id) {

        return service.findById(id);
    }

    @GetMapping("/between-date-of-birth")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findBetweenDateOfBirth(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {
        return service.findByDateOfBirthBetween(start, end);
    }

    @GetMapping("/doctor-department/{department}")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findByDoctorDepartment(@PathVariable String department) {
        return service.findByAdmittedByDepartment(department);
    }

    @GetMapping("/doctor-status")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> findByDoctorStatus(@RequestParam(defaultValue = "OFF") EmployeeStatus status) {
        return service.findByAdmittedByStatus(status);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@Valid @RequestBody PatientDto dto) {
        service.create(dto);

    }


    @PutMapping("/{patientId}")
    @ResponseStatus(HttpStatus.OK)
    public Patient update(@PathVariable(name = "patientId") Long id, @Valid @RequestBody PatientDto dto) {
        return service.update(id, dto);

    }
}
