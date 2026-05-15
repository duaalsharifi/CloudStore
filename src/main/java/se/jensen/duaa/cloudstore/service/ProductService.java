package se.jensen.duaa.cloudstore.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.jensen.duaa.cloudstore.model.Product;
import se.jensen.duaa.cloudstore.repository.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;
    private final RestTemplate restTemplate;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    public List<Product> fetchAndSaveProducts() {
        Product[] products = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                Product[].class
        );

        for (Product p : products) {
            // Hantera rating från API:et (som kommer som nested objekt)
            if (p.getRate() == null) {
                p.setRate(0.0);
            }
            if (p.getCount() == null) {
                p.setCount(0);
            }

            repository.save(p);
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
