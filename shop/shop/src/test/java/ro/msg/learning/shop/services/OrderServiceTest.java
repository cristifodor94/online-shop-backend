package ro.msg.learning.shop.services;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ro.msg.learning.shop.ShopApplication;
import ro.msg.learning.shop.dtos.OrderDTO;
import ro.msg.learning.shop.dtos.OrderDetailDTO;
import ro.msg.learning.shop.entities.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ShopApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
@RunWith(SpringRunner.class)

class OrderServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private StockService stockService;

    @Autowired
    private ObjectMapper objectMapper;
    private OrderDTO orderDTO;
    private Product productDellLaptop;
    private Product productRazerKeyboard;

    @BeforeEach
    private void populateDatabase() throws Exception {
        mockMvc.perform((post("/test/populate")));
        productDellLaptop = productService.findProductById(15);
        productRazerKeyboard = productService.findProductById(16);
        orderDTO = OrderDTO.builder().build();
        orderDTO.setCreated(LocalDateTime.now());
        orderDTO.setCountry("Romania");
        orderDTO.setCity("Ploiesti");
        orderDTO.setCounty("Prahova");
        orderDTO.setStreet("Calea Surii Mici");
    }

    private void createOrderSuccessfully() {
        List<OrderDetailDTO> products = new ArrayList<>();
        OrderDetailDTO orderDetailDTO = OrderDetailDTO.builder().productId(15).quantity(1).build();
        products.add(orderDetailDTO);
        orderDetailDTO = OrderDetailDTO.builder().productId(16).quantity(1).build();
        products.add(orderDetailDTO);
        orderDTO.setOrderDetails(products);
    }

    private void failToCreateOrder() {
        List<OrderDetailDTO> products = new ArrayList<>();
        OrderDetailDTO orderDetailDTO = OrderDetailDTO.builder().productId(15).quantity(500).build();
        products.add(orderDetailDTO);
        orderDetailDTO = OrderDetailDTO.builder().productId(16).quantity(300).build();
        products.add(orderDetailDTO);
        orderDTO.setOrderDetails(products);
    }

    @Test
    void createOrderSuccessfullySingleLocation() throws Exception {
        createOrderSuccessfully();
        mockMvc.perform(post("/orders")
                .content(objectMapper.writeValueAsString(orderDTO))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());
        assertThat(stockService.findAll().get(0).getLocation().equals(locationService.findLocationById(25)));
    }

    @Test
    void createOrderSuccessfullyMostAbundant() throws Exception {
        createOrderSuccessfully();
        mockMvc.perform(post("/orders")
                .content(objectMapper.writeValueAsString(orderDTO))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is2xxSuccessful());
        assertThat(stockService.findAll().get(0).getLocation().equals(locationService.findLocationById(26)));
    }

    @Test
    void missingStockException() throws Exception {
        failToCreateOrder();
        System.out.println(orderDTO);
        mockMvc.perform(post("/orders")
                .content(objectMapper.writeValueAsString(orderDTO))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is5xxServerError());
    }

    @AfterEach
    void clearDatabase() throws Exception {
        mockMvc.perform(post("/test/clear")).andExpect(status().isOk());
    }
}