package se.jensen.duaa.cloudstore.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.jensen.duaa.cloudstore.model.Product;
import se.jensen.duaa.cloudstore.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final RestTemplate restTemplate;

    @Value("${fakestore.api.url}")
    private String fakestoreApiUrl;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    public List<Product> fetchAndSaveProducts() {

        // Hämta produkter från URL i application.properties
        Product[] products = restTemplate.getForObject(
                fakestoreApiUrl,
                Product[].class
        );

        if (products != null) {
            for (Product p : products) {

                // Hantera rating om API:et skickar nested objekt
                if (p.getRate() == null) {
                    p.setRate(0.0);
                }
                if (p.getCount() == null) {
                    p.setCount(0);
                }

                repository.save(p);
            }
        }

        return repository.findAll();
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    public Product getProductById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found: " + id));
    }
}
