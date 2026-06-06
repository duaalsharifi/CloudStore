package se.jensen.duaa.cloudstore;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import se.jensen.duaa.cloudstore.model.Order;
import se.jensen.duaa.cloudstore.model.Product;
import se.jensen.duaa.cloudstore.repository.OrderRepository;
import se.jensen.duaa.cloudstore.service.OrderService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test") // detta använder application-test.properties
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    Order order;

    @BeforeEach
    void setUp() {
        Product product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");
        product.setPrice(99.99);

        orderService.placeOrder(product, "duaa");
    }

    @Test
    void canFindAllOrders() {
        List<Order> orders = orderRepository.findAll();

        assertNotNull(orders);
        assertEquals(1, orders.size());

        Order order = orders.get(0);

        assertEquals(1L, order.getProductId());
        assertEquals("Test Product", order.getProductName());
        assertEquals(99.99, order.getPrice());
        assertEquals("duaa", order.getUsername());
    }
}