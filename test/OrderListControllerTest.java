package com.springeasystock.easystock;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springeasystock.easystock.dto.CustomerDTO;
import com.springeasystock.easystock.dto.OrderListDTO;
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

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
public class OrderListControllerTest {
    @Autowired
    private MockMvc mockMvc;

    static private OrderListDTO orderListDTO;

    @BeforeAll
    static void createDTO() {
        orderListDTO = new OrderListDTO();
        orderListDTO.setOrderStatus("Boris");
        orderListDTO.setTotalPrice(29.90);
        orderListDTO.setOrderDate(Timestamp.valueOf("2024-11-26 14:30:00"));
        orderListDTO.setDeliveryDate(Timestamp.valueOf("2024-11-26 14:30:00"));
    }
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreation() throws Exception {
        String request = objectMapper.writeValueAsString(orderListDTO);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/orderlists")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse response = result.getResponse();

        OrderListDTO resultDTO = objectMapper.readValue(response.getContentAsString(), OrderListDTO.class);

        assertAll(
                () -> assertEquals(201, response.getStatus()),
                () -> assertEquals(orderListDTO.getOrderStatus(), resultDTO.getOrderStatus()),
                () -> assertEquals(orderListDTO.getTotalPrice(), resultDTO.getTotalPrice()),
                () -> assertEquals(orderListDTO.getOrderDate(), resultDTO.getOrderDate()),
                () -> assertEquals(orderListDTO.getDeliveryDate(), resultDTO.getDeliveryDate())
        );
    }

    @Test
    void testRetrieveCustomerById() throws Exception {
        String request = objectMapper.writeValueAsString(orderListDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/orderlists")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();

        OrderListDTO createdOrderList = objectMapper.readValue(createResponse.getContentAsString(), OrderListDTO.class);

        MvcResult retrieveResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/orderlists/" + createdOrderList.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse retrieveResponse = retrieveResult.getResponse();

        OrderListDTO retrievedOrderList = objectMapper.readValue(retrieveResponse.getContentAsString(), OrderListDTO.class);

        assertAll(
                () -> assertEquals(200, retrieveResponse.getStatus()),
                () -> assertEquals(createdOrderList.getId(), retrievedOrderList.getId()),
                () -> assertEquals(createdOrderList.getOrderStatus(), retrievedOrderList.getOrderStatus()),
                () -> assertEquals(createdOrderList.getTotalPrice(), retrievedOrderList.getTotalPrice()),
                () -> assertEquals(createdOrderList.getOrderDate(), retrievedOrderList.getOrderDate()),
                () -> assertEquals(createdOrderList.getDeliveryDate(), retrievedOrderList.getDeliveryDate())
        );
    }

    @Test
    void testUpdateCustomer() throws Exception {
        String request = objectMapper.writeValueAsString(orderListDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/orderlists")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();
        OrderListDTO createdOrderList = objectMapper.readValue(createResponse.getContentAsString(), OrderListDTO.class);

        OrderListDTO updatedOrderListDTO = new OrderListDTO();
        updatedOrderListDTO.setOrderStatus("Updated Status");
        updatedOrderListDTO.setTotalPrice(50.5);
        updatedOrderListDTO.setOrderDate(Timestamp.valueOf("2024-11-26 14:30:00"));
        updatedOrderListDTO.setDeliveryDate(Timestamp.valueOf("2024-11-26 14:30:00"));

        String updateRequest = objectMapper.writeValueAsString(updatedOrderListDTO);

        MvcResult updateResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/orderlists/" + createdOrderList.getId())
                        .content(updateRequest)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse updateResponse = updateResult.getResponse();
        OrderListDTO updatedOrderList = objectMapper.readValue(updateResponse.getContentAsString(), OrderListDTO.class);

        assertAll(
                () -> assertEquals(200, updateResponse.getStatus()),
                () -> assertEquals("Updated Status", updatedOrderList.getOrderStatus()),
                () -> assertEquals(50.5, updatedOrderList.getTotalPrice()),
                () -> assertEquals(Timestamp.valueOf("2024-11-26 14:30:00"), updatedOrderList.getOrderDate()),
                () -> assertEquals(Timestamp.valueOf("2024-11-26 14:30:00"), updatedOrderList.getDeliveryDate())
        );
    }

    @Test
    void testDeleteCustomer() throws Exception {
        String request = objectMapper.writeValueAsString(orderListDTO);

        MvcResult createResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/orderlists")
                        .content(request)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse createResponse = createResult.getResponse();
        OrderListDTO createdCustomer = objectMapper.readValue(createResponse.getContentAsString(), OrderListDTO.class);

        MvcResult deleteResult = mockMvc.perform(MockMvcRequestBuilders.delete("/api/orderlists/" + createdCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn();
        MockHttpServletResponse deleteResponse = deleteResult.getResponse();

        MvcResult retrieveResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/orderlists/" + createdCustomer.getId())
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
