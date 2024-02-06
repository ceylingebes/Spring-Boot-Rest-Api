package com.sisal.springbootapi.user;


import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {


    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;




    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository.findUserByEmail(user.getEmail());
        if (userByEmail.isPresent()){
            throw new IllegalStateException("This e-mail is already taken.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.findById(userId);
        boolean isExists = userRepository.existsById(userId);
        if (!isExists){
            throw new IllegalStateException("User does not exists with with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String name, String email, String password) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException(
                        "User does not exists with with ID: " + userId));
        if (name != null && !name.isEmpty() && !Objects.equals(user.getName(), name)){
            user.setName(name);
        }
        if (email != null && !email.isEmpty() && !Objects.equals(user.getEmail(), email)){
            Optional<User> userOptional = userRepository.findUserByEmail(email);
            if (userOptional.isPresent()) {
                throw new IllegalStateException("This e-mail is already taken.");
            }
            user.setEmail(email);
        }
        if (password != null && !password.isEmpty()) {
            if (passwordEncoder.matches(password, user.getPassword())){
                throw new IllegalStateException("New password cannot be the same as before.");
            }
            user.setPassword(passwordEncoder.encode(password));
        }
    }
}
