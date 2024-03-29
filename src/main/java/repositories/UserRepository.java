package repositories;

import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getAllUsers() {
        return users.stream().toList();
    }

    public Optional<User> getUserByLogin(String login) {
        return users.stream().filter(user -> user.getLogin().equals(login)).findAny();
    }

    public Optional<User> getUserByLoginAndPass(String login, String pass) {
        return users.stream().filter(user -> user.getLogin().equals(login) && user.getPassword().equals(pass)).findAny();
    }
}
