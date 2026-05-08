package se.jensen.duaa.cloudstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.duaa.cloudstore.model.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsername(String username);
}
