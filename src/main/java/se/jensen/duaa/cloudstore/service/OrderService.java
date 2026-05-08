package se.jensen.duaa.cloudstore.service;

import org.springframework.stereotype.Service;
import se.jensen.duaa.cloudstore.model.Order;
import se.jensen.duaa.cloudstore.model.Product;
import se.jensen.duaa.cloudstore.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository repo;

    public OrderService(OrderRepository repo) {
        this.repo = repo;

    }

    public void placeOrder(Product product, String username) {
        Order order = new Order();
        order.setProductId(product.getId());
        order.setProductName(product.getTitle());
        order.setPrice(product.getPrice());
        order.setUsername(username);

        repo.save(order);

    }

    public List<Order> getOrdersForUser(String username) {
        return repo.findByUsername(username);
    }
}