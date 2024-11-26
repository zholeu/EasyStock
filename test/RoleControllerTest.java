package com.springeasystock.easystock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springeasystock.easystock.dto.CustomerDTO;
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
public class RoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    static private CustomerDTO customerDTO;

    @BeforeAll
    static void createDTO() {
        customerDTO = new CustomerDTO();
        customerDTO.setName("Boris");
        customerDTO.setSurname("Johnson");
        customerDTO.setEmail("jojo@gmail.com");
        customerDTO.setAddress("Yellow st., dom 13, kv 10");
    }
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreation() throws Exception {
        String request = objectMapper.writeValueAsString(customerDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        CustomerDTO resultDTO = objectMapper.readValue(response.getContentAsString(), CustomerDTO.class);

        assertAll(
                () -> assertEquals(201, response.getStatus()),
                () -> assertEquals(customerDTO.getName(), resultDTO.getName()),
                () -> assertEquals(customerDTO.getSurname(), resultDTO.getSurname()),
                () -> assertEquals(customerDTO.getEmail(), resultDTO.getEmail()),
                () -> assertEquals(customerDTO.getAddress(), resultDTO.getAddress())
        );
    }

    @Test
    void testRetrieveCustomerById() throws Exception {
        String request = objectMapper.writeValueAsString(customerDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();

        CustomerDTO createdCustomer = objectMapper.readValue(createResponse.getContentAsString(), CustomerDTO.class);

        MvcResult retrieveResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/" + createdCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse retrieveResponse = retrieveResult.getResponse();

        CustomerDTO retrievedCustomer = objectMapper.readValue(retrieveResponse.getContentAsString(), CustomerDTO.class);

        assertAll(
                () -> assertEquals(200, retrieveResponse.getStatus()),
                () -> assertEquals(createdCustomer.getId(), retrievedCustomer.getId()),
                () -> assertEquals(createdCustomer.getName(), retrievedCustomer.getName()),
                () -> assertEquals(createdCustomer.getSurname(), retrievedCustomer.getSurname()),
                () -> assertEquals(createdCustomer.getEmail(), retrievedCustomer.getEmail()),
                () -> assertEquals(createdCustomer.getAddress(), retrievedCustomer.getAddress())
        );
    }

    @Test
    void testUpdateCustomer() throws Exception {
        String request = objectMapper.writeValueAsString(customerDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();
        CustomerDTO createdCustomer = objectMapper.readValue(createResponse.getContentAsString(), CustomerDTO.class);

        CustomerDTO updatedCustomerDTO = new CustomerDTO();
        updatedCustomerDTO.setName("Updated Name");
        updatedCustomerDTO.setSurname("Updated Surname");
        updatedCustomerDTO.setEmail("updated.email@example.com");
        updatedCustomerDTO.setAddress("Updated Address");

        String updateRequest = objectMapper.writeValueAsString(updatedCustomerDTO);

        MvcResult updateResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/customers/" + createdCustomer.getId())
                        .content(updateRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse updateResponse = updateResult.getResponse();
        CustomerDTO updatedCustomer = objectMapper.readValue(updateResponse.getContentAsString(), CustomerDTO.class);

        assertAll(
                () -> assertEquals(200, updateResponse.getStatus()),
                () -> assertEquals("Updated Name", updatedCustomer.getName()),
                () -> assertEquals("Updated Surname", updatedCustomer.getSurname()),
                () -> assertEquals("updated.email@example.com", updatedCustomer.getEmail()),
                () -> assertEquals("Updated Address", updatedCustomer.getAddress())
        );
    }

    @Test
    void testDeleteCustomer() throws Exception {
        String request = objectMapper.writeValueAsString(customerDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();
        CustomerDTO createdCustomer = objectMapper.readValue(createResponse.getContentAsString(), CustomerDTO.class);

        MvcResult deleteResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/customers/" + createdCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse deleteResponse = deleteResult.getResponse();

        MvcResult retrieveResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/customers/" + createdCustomer.getId())
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
