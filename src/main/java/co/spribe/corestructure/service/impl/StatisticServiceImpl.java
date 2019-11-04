package co.spribe.corestructure.service.impl;

import co.spribe.corestructure.service.StatisticService;
import co.spribe.corestructure.statistic.model.UserAction;
import co.spribe.corestructure.statistic.repository.UserActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticServiceImpl implements StatisticService {

    private UserActionRepository userActionRepository;

    @Autowired
    public void setUserActionRepository(UserActionRepository userActionRepository) {
        this.userActionRepository = userActionRepository;
    }

    @Override
    public UserAction addUserAction(UserAction userAction) {
        UserAction insertedUserAction = userActionRepository.save(userAction);
        return insertedUserAction;
    }
}
