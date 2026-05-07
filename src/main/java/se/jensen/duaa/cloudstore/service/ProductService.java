package se.jensen.duaa.cloudstore.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.jensen.duaa.cloudstore.model.Product;
import se.jensen.duaa.cloudstore.repository.ProductRepository;

import java.util.Arrays;
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
        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        headers.set("Accept", "application/json");

        HttpEntity<Void> entity = new HttpEntity<>(headers);


        ResponseEntity<Product[]> response = restTemplate.exchange(
                "https://fakestoreapi.com/products",
                HttpMethod.GET,
                entity,
                Product[].class
        );

        Product[] prod = response.getBody();

        List<Product> products = Arrays.asList(prod);
        repository.saveAll(products);
        return repository.findAll();
    }

    public List<Product> getAllProducts() {
        return repository.findAll();
    }

}