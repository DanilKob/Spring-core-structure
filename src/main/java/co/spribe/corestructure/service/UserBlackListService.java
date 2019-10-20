package co.spribe.corestructure.service;

import co.spribe.corestructure.ping.model.User;

public interface UserBlackListService {
    boolean doesExist(User user);
}
