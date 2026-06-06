package se.jensen.duaa.cloudstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import se.jensen.duaa.cloudstore.model.User;
import se.jensen.duaa.cloudstore.repository.UserRepository;
import se.jensen.duaa.cloudstore.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        userService.register("duaa", "12345");
    }

    @Test
    void userIsSavedWithHashedPassword() {
        User user = userRepository.findByUsername("duaa")
                .orElseThrow(() -> new RuntimeException("User not found"));

        assertNotNull(user);
        assertEquals("duaa", user.getUsername());

        // lösenordet får INTE vara i klartext
        assertNotEquals("12345", user.getPassword());

        // bcrypt-hash börjar alltid med $2
        assertTrue(user.getPassword().startsWith("$2"));

        // roll USER
        assertEquals("USER", user.getRole());
    }
}
