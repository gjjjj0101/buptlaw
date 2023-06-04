package edu.bupt.law.user.config;


import edu.bupt.law.user.repository.UserRepository;
import edu.bupt.law.user.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Initialize {

    @Bean
    CommandLineRunner init(UserServiceImpl userService, UserRepository userRepository){
        return args -> {
            if(userRepository.findByUsername("123") == null){
                userService.registerUserByUsernameAndPassword("123","123");
            }

        };
    }
}
