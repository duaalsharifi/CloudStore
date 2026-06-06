package se.jensen.duaa.cloudstore.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.jensen.duaa.cloudstore.model.User;
import se.jensen.duaa.cloudstore.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository repo;
    //“Jag måste spara användare säkert → jag behöver PasswordEncoder.”
    private final PasswordEncoder encoder;

    public UserService(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    public void register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setRole("USER");
        repo.save(user);
    }
}
