package application.service;

import application.dto.UserDto;
import application.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(UserDto user);

    User findByUserEmail(String username);
}
