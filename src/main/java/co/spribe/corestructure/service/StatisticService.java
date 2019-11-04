package co.spribe.corestructure.service;

import co.spribe.corestructure.statistic.model.UserAction;

public interface StatisticService {
    UserAction addUserAction(UserAction userAction);
}
