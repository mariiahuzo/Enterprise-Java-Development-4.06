package com.ironhack.Lab_4_06.Lab_4_06.repository;

import com.ironhack.Lab_4_06.Lab_4_06.models.EmployeeStatus;
import com.ironhack.Lab_4_06.Lab_4_06.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface PatientRepository extends JpaRepository <Patient, Long> {
    List<Patient> findByDateOfBirthBetween(Date date1, Date date2);
    List<Patient> findByAdmittedByDepartment(String department);

    List<Patient> findByAdmittedByStatus(EmployeeStatus status);
}
