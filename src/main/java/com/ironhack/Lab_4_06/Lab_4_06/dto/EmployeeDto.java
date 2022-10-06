package com.ironhack.Lab_4_06.Lab_4_06.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.Lab_4_06.Lab_4_06.models.EmployeeStatus;
import com.ironhack.Lab_4_06.Lab_4_06.models.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {
    private Long employeeId;
    @NotEmpty
    @Size(min = 2, message = "Department should have at least 2 characters")
    private String department;
    @NotEmpty
    @Size(min = 2, message = "Employee name should have at least 2 characters")
    private String name;
    private EmployeeStatus status;
    @JsonIgnore
    private List<Patient> patients;
}
