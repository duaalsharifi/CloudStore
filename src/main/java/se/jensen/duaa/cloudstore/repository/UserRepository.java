package se.jensen.duaa.cloudstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.duaa.cloudstore.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
