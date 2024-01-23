package com.sisal.springbootapi.user;


import java.util.List;

public class UserService {
    public List<User> getUsers(){
        return List.of(
                new User(
                        1L,
                        "Ceylin",
                        "ceylingebes@gmail.com",
                        "mypassword123")
        );
    }
}
