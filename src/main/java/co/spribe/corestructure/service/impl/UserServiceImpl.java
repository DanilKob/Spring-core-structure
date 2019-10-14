package co.spribe.corestructure.service.impl;

import co.spribe.corestructure.model.User;
import co.spribe.corestructure.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public Optional<User> getUserByLogin(String login) {
        Optional<User> user = Optional.empty();

        return user;
    }
}
