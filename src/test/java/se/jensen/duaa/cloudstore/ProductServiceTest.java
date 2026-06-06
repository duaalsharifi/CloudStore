package se.jensen.duaa.cloudstore.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import se.jensen.duaa.cloudstore.model.Product;
import se.jensen.duaa.cloudstore.repository.ProductRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Autowired
    ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setup() {
        product = new Product();
        product.setId(1L);
        product.setTitle("Test Product");
        product.setPrice(199.0);
        product.setImage("test.jpg");
        product.setRate(4.5);
        product.setCount(10);

        productRepository.save(product);
    }

    @Test
    void canFindAllProducts() {
        List<Product> products = productService.getAllProducts();

        assertNotNull(products);
        assertEquals(1, products.size());
        assertEquals("Test Product", products.get(0).getTitle());
    }

    @Test
    void canFindProductById() {
        Product found = productService.getProductById(1L);

        assertNotNull(found);
        assertEquals("Test Product", found.getTitle());
        assertEquals(199.0, found.getPrice());
    }
}
