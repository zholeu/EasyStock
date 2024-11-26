package com.springeasystock.easystock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springeasystock.easystock.dto.CustomerDTO;
import com.springeasystock.easystock.dto.EmployeeDTO;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    static private EmployeeDTO employeeDTO;

    @BeforeAll
    static void createDTO() {
        employeeDTO = new EmployeeDTO();
        employeeDTO.setUsername("olegan");
        employeeDTO.setRole("Sorting Person");
    }
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreation() throws Exception {
        String request = objectMapper.writeValueAsString(employeeDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        EmployeeDTO resultDTO = objectMapper.readValue(response.getContentAsString(), EmployeeDTO.class);

        assertAll(
                () -> assertEquals(201, response.getStatus()),
                () -> assertEquals(employeeDTO.getUsername(), resultDTO.getUsername()),
                () -> assertEquals(employeeDTO.getRole(), resultDTO.getRole()),
                () -> assertEquals(employeeDTO.getCreatedAt(), resultDTO.getCreatedAt())
        );
    }

    @Test
    void testRetrieveCustomerById() throws Exception {
        String request = objectMapper.writeValueAsString(employeeDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();

        EmployeeDTO createdEmployee = objectMapper.readValue(createResponse.getContentAsString(), EmployeeDTO.class);

        MvcResult retrieveResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/" + createdEmployee.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse retrieveResponse = retrieveResult.getResponse();

        EmployeeDTO retrievedCustomer = objectMapper.readValue(retrieveResponse.getContentAsString(), EmployeeDTO.class);

        assertAll(
                () -> assertEquals(200, retrieveResponse.getStatus()),
                () -> assertEquals(createdEmployee.getId(), retrievedCustomer.getId()),
                () -> assertEquals(createdEmployee.getUsername(), retrievedCustomer.getUsername()),
                () -> assertEquals(createdEmployee.getRole(), retrievedCustomer.getRole()),
                () -> assertEquals(createdEmployee.getCreatedAt(), retrievedCustomer.getCreatedAt())
        );
    }

    @Test
    void testUpdateCustomer() throws Exception {
        String request = objectMapper.writeValueAsString(employeeDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();
        EmployeeDTO createdCustomer = objectMapper.readValue(createResponse.getContentAsString(), EmployeeDTO.class);

        EmployeeDTO updatedCustomerDTO = new EmployeeDTO();
        updatedCustomerDTO.setUsername("Updated user");
        updatedCustomerDTO.setRole("Updated role");

        String updateRequest = objectMapper.writeValueAsString(updatedCustomerDTO);

        MvcResult updateResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/employee/" + createdCustomer.getId())
                        .content(updateRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse updateResponse = updateResult.getResponse();
        EmployeeDTO updated = objectMapper.readValue(updateResponse.getContentAsString(), EmployeeDTO.class);

        assertAll(
                () -> assertEquals(200, updateResponse.getStatus()),
                () -> assertEquals("Updated user", updated.getUsername()),
                () -> assertEquals("Updated role", updated.getRole())
        );
    }

    @Test
    void testDeleteCustomer() throws Exception {
        String request = objectMapper.writeValueAsString(employeeDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/employee")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();
        EmployeeDTO created = objectMapper.readValue(createResponse.getContentAsString(), EmployeeDTO.class);

        MvcResult deleteResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/employee/" + created.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse deleteResponse = deleteResult.getResponse();

        MvcResult retrieveResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/employee/" + created.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse retrieveResponse = retrieveResult.getResponse();

        assertAll(
                () -> assertEquals(200, deleteResponse.getStatus()),
                () -> assertEquals("Deleted Successfully!", deleteResponse.getContentAsString()),
                () -> assertEquals(404, retrieveResponse.getStatus())
        );
    }
}
