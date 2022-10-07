package com.ironhack.Lab_4_06.Lab_4_06.service;


import com.ironhack.Lab_4_06.Lab_4_06.dto.PatientDto;
import com.ironhack.Lab_4_06.Lab_4_06.models.Employee;
import com.ironhack.Lab_4_06.Lab_4_06.models.EmployeeStatus;
import com.ironhack.Lab_4_06.Lab_4_06.models.Patient;
import com.ironhack.Lab_4_06.Lab_4_06.repository.EmployeeRepository;
import com.ironhack.Lab_4_06.Lab_4_06.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository repository;
    private final EmployeeRepository employeeRepository;

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

    public Patient update(Long patientId, PatientDto dto) {

        Patient patient = repository.findById(patientId).orElseThrow();

        patient.setName(dto.getName());
        patient.setDateOfBirth(dto.getDateOfBirth());
        Employee employee = employeeRepository.findById(dto.getEmployeeId()).orElseThrow();
        patient.setAdmittedBy(employee);
        return repository.save(patient);
    }

    public Patient create(PatientDto dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId()).orElseThrow();
        Patient patient = new Patient();
        patient.setName(dto.getName());
        patient.setDateOfBirth(dto.getDateOfBirth());
        patient.setAdmittedBy(employee);

        return repository.save(patient);
    }
}
