package co.spribe.corestructure.service;

import co.spribe.corestructure.model.User;

public interface UserBlackListService {
    boolean doesExist(User user);
}
