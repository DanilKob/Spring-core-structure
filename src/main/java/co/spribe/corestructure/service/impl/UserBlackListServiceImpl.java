package co.spribe.corestructure.service.impl;

import co.spribe.corestructure.model.User;
import co.spribe.corestructure.service.UserBlackListService;
import org.springframework.stereotype.Service;

@Service
public class UserBlackListServiceImpl implements UserBlackListService {
    @Override
    public boolean doesExist(User user) {
        return false;
    }
}
