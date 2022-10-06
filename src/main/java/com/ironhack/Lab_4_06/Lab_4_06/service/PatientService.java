package com.ironhack.Lab_4_06.Lab_4_06.service;


import com.ironhack.Lab_4_06.Lab_4_06.models.EmployeeStatus;
import com.ironhack.Lab_4_06.Lab_4_06.models.Patient;
import com.ironhack.Lab_4_06.Lab_4_06.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository repository;

    public List<Patient> findAll() {
        return repository.findAll();
    }

    public Patient findById(Long id) {
        return repository.findById(id).orElseThrow();

    }

    public List<Patient> findByDateOfBirthBetween(Date start, Date end) {
        return repository.findByDateOfBirthBetween(start, end);

    }

    public List<Patient> findByAdmittedByDepartment(String department) {
        return repository.findByAdmittedByDepartment(department);
    }

    public List<Patient> findByAdmittedByStatus(EmployeeStatus status) {
        return repository.findByAdmittedByStatus(status);
    }
}
