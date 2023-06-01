package edu.bupt.buptlaw.service.impl;

import edu.bupt.buptlaw.exception.user.UserException;
import edu.bupt.buptlaw.exception.user.UserExistException;
import edu.bupt.buptlaw.model.User;
import edu.bupt.buptlaw.repository.UserRepository;
import edu.bupt.buptlaw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return Optional.ofNullable(user)
                .orElseThrow(() -> new UsernameNotFoundException("用户" + username + "不存在"));
    }

    @Override
    public User registerUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            throw new UserExistException("用户" + username +"已存在");
        }
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRoles(Arrays.asList("ROLE_USER"));
        userRepository.save(newUser);
        return newUser;
    }
}
