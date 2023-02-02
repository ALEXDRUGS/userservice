package services;

import model.User;

import java.util.List;

public interface UserService {

    void createUser(String login, String pass);

    List<String> getAllLogins();
}
