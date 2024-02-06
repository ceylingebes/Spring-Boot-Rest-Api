package com.sisal.springbootapi.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User ceylin = new User("Ceylin", "ceylin_example@gmail.com", "123abc");
            User kemal = new User("Kemal", "kemal_example@gmail.com", "password123abc");
            repository.saveAll(List.of(ceylin, kemal));
        };
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
