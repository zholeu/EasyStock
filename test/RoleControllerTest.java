package com.springeasystock.easystock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springeasystock.easystock.dto.CustomerDTO;
import com.springeasystock.easystock.dto.RoleDTO;
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

    static private RoleDTO roleDTO;

    @BeforeAll
    static void createDTO() {
        roleDTO = new RoleDTO();
        roleDTO.setName("Data Entry Specialist");
    }
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreation() throws Exception {
        String request = objectMapper.writeValueAsString(roleDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/roles")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        RoleDTO resultDTO = objectMapper.readValue(response.getContentAsString(), RoleDTO.class);

        assertAll(
                () -> assertEquals(201, response.getStatus()),
                () -> assertEquals(roleDTO.getName(), resultDTO.getName())
        );
    }

    @Test
    void testRetrieveCustomerById() throws Exception {
        String request = objectMapper.writeValueAsString(roleDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/roles")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();

        RoleDTO createdRole = objectMapper.readValue(createResponse.getContentAsString(), RoleDTO.class);

        MvcResult retrieveResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/roles/" + createdRole.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse retrieveResponse = retrieveResult.getResponse();

        RoleDTO retrievedCustomer = objectMapper.readValue(retrieveResponse.getContentAsString(), RoleDTO.class);

        assertAll(
                () -> assertEquals(200, retrieveResponse.getStatus()),
                () -> assertEquals(createdRole.getId(), retrievedCustomer.getId()),
                () -> assertEquals(createdRole.getName(), retrievedCustomer.getName())
        );
    }

    @Test
    void testUpdateCustomer() throws Exception {
        String request = objectMapper.writeValueAsString(roleDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/roles")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();
        RoleDTO createdCustomer = objectMapper.readValue(createResponse.getContentAsString(), RoleDTO.class);

        RoleDTO updatedCustomerDTO = new RoleDTO();
        updatedCustomerDTO.setName("Updated Role");

        String updateRequest = objectMapper.writeValueAsString(updatedCustomerDTO);

        MvcResult updateResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/roles/" + createdCustomer.getId())
                        .content(updateRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse updateResponse = updateResult.getResponse();
        RoleDTO updatedCustomer = objectMapper.readValue(updateResponse.getContentAsString(), RoleDTO.class);

        assertAll(
                () -> assertEquals(200, updateResponse.getStatus()),
                () -> assertEquals("Updated Role", updatedCustomer.getName())
        );
    }

    @Test
    void testDeleteCustomer() throws Exception {
        String request = objectMapper.writeValueAsString(roleDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/roles")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();
        RoleDTO createdCustomer = objectMapper.readValue(createResponse.getContentAsString(), RoleDTO.class);

        MvcResult deleteResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/roles/" + createdCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse deleteResponse = deleteResult.getResponse();

        MvcResult retrieveResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/roles/" + createdCustomer.getId())
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
