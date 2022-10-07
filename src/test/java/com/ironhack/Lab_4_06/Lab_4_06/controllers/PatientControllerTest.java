package com.ironhack.Lab_4_06.Lab_4_06.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Lab_4_06.Lab_4_06.models.Employee;
import com.ironhack.Lab_4_06.Lab_4_06.models.EmployeeStatus;
import com.ironhack.Lab_4_06.Lab_4_06.models.Patient;
import com.ironhack.Lab_4_06.Lab_4_06.repository.EmployeeRepository;
import com.ironhack.Lab_4_06.Lab_4_06.repository.PatientRepository;
import com.ironhack.Lab_4_06.Lab_4_06.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port.8080"}
)
public class PatientControllerTest {
    private static final String URL = "/patient";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EmployeeService service;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @AfterEach
    public void tearDown() {
        patientRepository.deleteAll();
    }


    @Test
    public void findAllPatients() throws Exception {
        mockMvc.perform(get(URL)).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void findAllEmployeeById() throws Exception {
        Patient p = savePatient();
        var all = repository.findAll();
        mockMvc.perform(get(URL + "/1")).andDo(print())
                .andExpect(status().is2xxSuccessful());


    }

    private Patient savePatient() {
        var empl1 = new Employee();
        empl1.setEmployeeId(1L);
        empl1.setStatus(EmployeeStatus.OFF);
        empl1.setDepartment("department");
        empl1.setName("Alonso Flores");
        empl1.setPatients(List.of());
        repository.saveAll(List.of(empl1));


        Patient p = new Patient();
        p.setPatientId(1L);
        p.setName("Bob");
        p.setAdmittedBy(empl1);
        p.setDateOfBirth(LocalDate.now());

        return patientRepository.save(p);

    }

}
