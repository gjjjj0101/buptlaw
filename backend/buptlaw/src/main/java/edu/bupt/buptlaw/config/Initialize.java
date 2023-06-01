package edu.bupt.buptlaw.config;


import edu.bupt.buptlaw.repository.UserRepository;
import edu.bupt.buptlaw.service.UserService;
import edu.bupt.buptlaw.service.impl.UserServiceImpl;
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
