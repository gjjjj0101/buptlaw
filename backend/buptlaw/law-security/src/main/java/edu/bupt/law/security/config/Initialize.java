package edu.bupt.law.security.config;


import edu.bupt.law.security.repository.UserRepository;
import edu.bupt.law.security.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Initialize {

    @Bean
    CommandLineRunner init(UserServiceImpl userService, UserRepository userRepository){
        return args -> {
            if(userRepository.findByUsername("root") == null){
                userService.registerUserByUsernameAndPassword("root","root");
            }

        };
    }
}
