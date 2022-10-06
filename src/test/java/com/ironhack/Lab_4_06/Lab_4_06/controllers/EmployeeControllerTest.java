package com.ironhack.Lab_4_06.Lab_4_06.controllers;

import com.ironhack.Lab_4_06.Lab_4_06.models.Employee;
import com.ironhack.Lab_4_06.Lab_4_06.models.EmployeeStatus;
import com.ironhack.Lab_4_06.Lab_4_06.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {"server.port.6000"}
)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class EmployeeControllerTest {

    private static final String URL = "/employee";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private EmployeeRepository repository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

    }

    @Test
    public void findAllEmployees() throws Exception {
        this.mockMvc.perform(get(URL)).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }


    @Test
    public void findAllEmployeeById() throws Exception {
        saveEmployee();
        this.mockMvc.perform(get(URL +"/{id}", 1)).andDo(print())
                .andExpect(status().is2xxSuccessful());


    }

    private void saveEmployee() {

        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setDepartment("test");
        employee.setName("Devin");
        employee.setStatus(EmployeeStatus.ON);
        repository.save(employee);
    }
}
