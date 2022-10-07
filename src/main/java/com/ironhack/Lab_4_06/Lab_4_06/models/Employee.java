package com.ironhack.Lab_4_06.Lab_4_06.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
    @Id
    private Long employeeId;
    private String department;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private EmployeeStatus status;
    @OneToMany(mappedBy = "admittedBy",fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Patient> patients;

}
