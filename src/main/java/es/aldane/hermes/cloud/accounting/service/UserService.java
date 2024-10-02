package es.aldane.hermes.cloud.accounting.service;


import es.aldane.hermes.cloud.accounting_api_server_java.model.User;

import java.util.List;

public interface UserService {

    List<User> getUsers(List<String> userIds);

    User getUserById(Long userId);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    User createUser(User user);

    boolean deleteUser(Long id);

    User updateUser(User user);
}
