package com.sisal.springbootapi.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {


    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;


    @GetMapping
    public List<User> getUsers(){

        return userService.getUsers();
    }

    @PostMapping
    public ResponseEntity<String> registerNewUser(@RequestBody User user){
        try{
            userService.addNewUser(user);
            return new ResponseEntity<>("Success at new user registry.", HttpStatus.CREATED);
        }
        catch(IllegalStateException e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long userId){
        try {
            userService.deleteUser(userId);
            return new ResponseEntity<>(String.format("Success at deleting user with id %s.", userId), HttpStatus.OK);
        }
        catch (IllegalStateException e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(path = "{userId}")
    public ResponseEntity<String> updateUser(@PathVariable("userId") Long userId,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String email,
                                             @RequestBody(required = false) String password) {
        try {
            userService.updateUser(userId, name, email, password);
            return new ResponseEntity<>(String.format("Success at updating user with id: %s", userId), HttpStatus.OK);
        }
        catch(IllegalStateException e){
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }
}
