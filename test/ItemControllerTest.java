package com.springeasystock.easystock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springeasystock.easystock.dto.CustomerDTO;
import com.springeasystock.easystock.dto.ItemDTO;
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
public class ItemControllerTest {
    @Autowired
    private MockMvc mockMvc;

    static private ItemDTO itemDTO;

    @BeforeAll
    static void createDTO() {
        itemDTO = new ItemDTO();
        itemDTO.setName("Mirror");
        itemDTO.setSupplier("Will's Mirrors");
        itemDTO.setSize(13.9F);
        itemDTO.setPrice(1000.97F);
        itemDTO.setAsile(2);
        itemDTO.setRack(2);
        itemDTO.setShelf(2);
    }
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreation() throws Exception {
        String request = objectMapper.writeValueAsString(itemDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/items")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        ItemDTO resultDTO = objectMapper.readValue(response.getContentAsString(), ItemDTO.class);

        assertAll(
                () -> assertEquals(201, response.getStatus()),
                () -> assertEquals(itemDTO.getName(), resultDTO.getName()),
                () -> assertEquals(itemDTO.getSupplier(), resultDTO.getSupplier()),
                () -> assertEquals(itemDTO.getSize(), resultDTO.getSize()),
                () -> assertEquals(itemDTO.getPrice(), resultDTO.getPrice()),
                () -> assertEquals(itemDTO.getAsile(), resultDTO.getAsile()),
                () -> assertEquals(itemDTO.getRack(), resultDTO.getRack()),
                () -> assertEquals(itemDTO.getShelf(), resultDTO.getShelf())
        );
    }

    @Test
    void testRetrieveCustomerById() throws Exception {
        String request = objectMapper.writeValueAsString(itemDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/items")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();

        ItemDTO createdItem = objectMapper.readValue(createResponse.getContentAsString(), ItemDTO.class);

        MvcResult retrieveResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/items/" + createdItem.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse retrieveResponse = retrieveResult.getResponse();

        ItemDTO retrievedCustomer = objectMapper.readValue(retrieveResponse.getContentAsString(), ItemDTO.class);

        assertAll(
                () -> assertEquals(200, retrieveResponse.getStatus()),
                () -> assertEquals(createdItem.getName(), retrievedCustomer.getName()),
                () -> assertEquals(createdItem.getSupplier(), retrievedCustomer.getSupplier()),
                () -> assertEquals(createdItem.getSize(), retrievedCustomer.getSize()),
                () -> assertEquals(createdItem.getPrice(), retrievedCustomer.getPrice()),
                () -> assertEquals(createdItem.getAsile(), retrievedCustomer.getAsile()),
                () -> assertEquals(createdItem.getRack(), retrievedCustomer.getRack()),
                () -> assertEquals(createdItem.getShelf(), retrievedCustomer.getShelf())

        );
    }

    @Test
    void testUpdateCustomer() throws Exception {
        String request = objectMapper.writeValueAsString(itemDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/items")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();
        ItemDTO createdCustomer = objectMapper.readValue(createResponse.getContentAsString(), ItemDTO.class);

        ItemDTO updatedItemDTO = new ItemDTO();
        updatedItemDTO.setName("Updated Name");
        updatedItemDTO.setSupplier("Updated Supplier");
        updatedItemDTO.setSize(10F);
        updatedItemDTO.setPrice(2000F);
        updatedItemDTO.setAsile(5);
        updatedItemDTO.setRack(8);
        updatedItemDTO.setShelf(9);

        String updateRequest = objectMapper.writeValueAsString(updatedItemDTO);

        MvcResult updateResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/items/" + createdCustomer.getId())
                        .content(updateRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse updateResponse = updateResult.getResponse();
        ItemDTO updatedItem = objectMapper.readValue(updateResponse.getContentAsString(), ItemDTO.class);

        assertAll(
                () -> assertEquals(200, updateResponse.getStatus()),
                () -> assertEquals("Updated Name", updatedItem.getName()),
                () -> assertEquals("Updated Supplier", updatedItem.getSupplier()),
                () -> assertEquals(10F, updatedItem.getSize()),
                () -> assertEquals(2000F, updatedItem.getPrice()),
                () -> assertEquals(5, updatedItem.getAsile()),
                () -> assertEquals(8, updatedItem.getRack()),
                () -> assertEquals(9, updatedItem.getShelf())
        );
    }

    @Test
    void testDeleteCustomer() throws Exception {
        String request = objectMapper.writeValueAsString(itemDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/items")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();
        ItemDTO createdItem = objectMapper.readValue(createResponse.getContentAsString(), ItemDTO.class);

        MvcResult deleteResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/items/" + createdItem.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse deleteResponse = deleteResult.getResponse();

        MvcResult retrieveResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/items/" + createdItem.getId())
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
