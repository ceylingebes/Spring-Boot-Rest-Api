package com.sisal.springbootapi.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            User ceylin = new User("Ceylin", "ceylingebes@gmail.com", "123abc");
            User kemal = new User("Kemal", "kemallbalci@gmail.com", "123abc");
            repository.saveAll(List.of(ceylin, kemal));
        };
    }
}
