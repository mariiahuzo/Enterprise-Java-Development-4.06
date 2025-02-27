package com.ironhack.Lab_4_06.Lab_4_06.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.Lab_4_06.Lab_4_06.dto.EmployeeDto;
import com.ironhack.Lab_4_06.Lab_4_06.models.Employee;
import com.ironhack.Lab_4_06.Lab_4_06.models.EmployeeStatus;
import com.ironhack.Lab_4_06.Lab_4_06.repository.EmployeeRepository;
import com.ironhack.Lab_4_06.Lab_4_06.repository.PatientRepository;
import com.ironhack.Lab_4_06.Lab_4_06.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port.8080"}
)
public class EmployeeControllerTest {

    private static final String URL = "/employee";

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
        repository.deleteAll();
    }

    @Test
    public void findAllEmployees() throws Exception {
        mockMvc.perform(get(URL)).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void findAllEmployeeById() throws Exception {
        saveEmployees(EmployeeStatus.OFF, "department");
        var all = repository.findAll();
        mockMvc.perform(get(URL + "/1")).andDo(print())
                .andExpect(status().is2xxSuccessful());


    }

    @Test
    void saveEmployee() throws Exception {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeId(55L);
        employeeDto.setName("Pepe");
        employeeDto.setStatus(EmployeeStatus.OFF);
        employeeDto.setDepartment("immunology");
        String body = objectMapper.writeValueAsString(employeeDto);
        mockMvc.perform(post("/employee").content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status()
                        .isCreated());

    }

    @Test
    void updateStatus() throws Exception {
        saveEmployees(EmployeeStatus.OFF, "department");
        var t = repository.findAll();
        mockMvc.perform(patch("/employee/{employeeId}/status", 1L)
                        .param("status", "ON")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(repository.findById(1L).get().getStatus(), EmployeeStatus.ON);
    }


    @Test
    void updateDepartment() throws Exception {
        saveEmployees(EmployeeStatus.OFF, "department");
        var t = repository.findAll();
        mockMvc.perform(patch("/employee/{employeeId}/department", 1L)
                        .param("department", "cardiology")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        assertEquals(repository.findById(1L).get().getDepartment(), "cardiology");
    }


    private void saveEmployees(EmployeeStatus status, String department) {
        var empl1 = new Employee();
        empl1.setEmployeeId(1L);
        empl1.setStatus(status);
        empl1.setDepartment(department);
        empl1.setName("Alonso Flores");
        empl1.setPatients(List.of());

        repository.saveAll(List.of(empl1));
    }

}
