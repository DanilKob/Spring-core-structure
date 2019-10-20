package co.spribe.corestructure.service;

import co.spribe.corestructure.ping.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByLogin(String login);
}
