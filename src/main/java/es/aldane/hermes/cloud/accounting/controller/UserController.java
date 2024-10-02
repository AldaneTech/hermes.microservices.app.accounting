package es.aldane.hermes.cloud.accounting.controller;


import es.aldane.hermes.cloud.accounting.service.UserService;
import es.aldane.hermes.cloud.accounting_api_server_java.api.UserApi;
import es.aldane.hermes.cloud.accounting_api_server_java.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController implements UserApi {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<User> createUser(@Valid User user) {
        var newUser = userService.createUser(user);
        return newUser != null ? ResponseEntity.ok(newUser) : ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        var user = userService.deleteUser(userId);
        return user ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<User> getUserByEmail(String email) {
        var user = userService.getUserByEmail(email);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<User> getUserById(Long userId) {
        var user = userService.getUserById(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<User> getUserByUsername(String username) {
        var user = userService.getUserByUsername(username);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        var states = userService.getUsers(new ArrayList<>());
        return ResponseEntity.ok(states);
    }

    @Override
    public ResponseEntity<User> updateUser(@Valid User user) {
        return userService.updateUser(user) != null ? ResponseEntity.ok(user) : ResponseEntity.badRequest().build();
    }
}
