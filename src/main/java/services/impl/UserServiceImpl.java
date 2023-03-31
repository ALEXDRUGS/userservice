package services.impl;

import exceptions.UserNonUniqueException;
import model.User;
import repositories.UserRepository;
import services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public void createUser(String login, String pass) {
        if ((login != null && !login.isEmpty())
                && (pass != null && !pass.isEmpty()))
            try {
                if (!verificationUser(login, pass)) {
                    getUserRepository().addUser(new User(login, pass));
                }else throw new UserNonUniqueException();
            } catch (UserNonUniqueException e) {
                e.getStackTrace("This user exists");
            }
        else throw new IllegalArgumentException();
    }

    public boolean verificationUser(String login, String pass) {
        return getUserRepository()
                .getAllUsers()
                .stream()
                .anyMatch(user -> user.getLogin().equals(login)
                        && user.getPassword().equals(pass));
    }

    @Override
    public List<String> getAllLogins() {
        return getUserRepository().getAllUsers().stream().map(User::getLogin).toList();
    }
}
