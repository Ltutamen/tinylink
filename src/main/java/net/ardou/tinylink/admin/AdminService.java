package net.ardou.tinylink.admin;

import net.ardou.tinylink.user.Role;
import net.ardou.tinylink.user.User;
import net.ardou.tinylink.user.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class AdminService {

    final UserRepository userRepository;

    AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    List<UserDto> getUsers() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .map(UserDto::new).toList();
    }

    @Transactional
    void promoteToAdmin(String email) {
        userRepository.findByMail(email)
                .ifPresent(user -> {
                    user.setRole(Role.ADMIN);
                    userRepository.save(user);
                });

    }

    void banUser(String email) {
        userRepository.findByMail(email)
                .ifPresent(user -> {
                    user.ban();
                    userRepository.save(user);
                });
    }

}
