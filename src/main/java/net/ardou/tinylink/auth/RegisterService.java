package net.ardou.tinylink.auth;

import net.ardou.tinylink.auth.domain.RegisterBody;
import net.ardou.tinylink.user.User;
import net.ardou.tinylink.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RegisterService {

    final UserRepository userRepository;
    final PasswordEncoder passwordEncoder;

    public RegisterService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param registerBody from client
     * @return empty optional if registration is successfully, Optional with error message otherwise
     */
    @Transactional
    Optional<String> registerUser(RegisterBody registerBody) {
        var userOptional = userRepository.findByMail(registerBody.getEmail());
        if (userOptional.isPresent()) {
            return Optional.of("User email already exists");
        } else {
            final String encryptedPassword = passwordEncoder.encode(registerBody.getPassword());
            User user = User.createUser(registerBody.getEmail(), encryptedPassword);
            user = userRepository.save(user);
            return Optional.empty();
        }
    }
}
