package com.sisal.springbootapi.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()){
            throw new IllegalStateException("This e-mail is already taken.");
        }
        userRepository.save(user);
    }

    public void deleteStudent(Long userId) {
        userRepository.findById(userId);
        boolean isExists = userRepository.existsById(userId);
        if (!isExists){
            throw new IllegalStateException("User does not exists with with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }
}
