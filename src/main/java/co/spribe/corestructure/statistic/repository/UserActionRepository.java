package co.spribe.corestructure.statistic.repository;

import co.spribe.corestructure.statistic.model.UserAction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActionRepository extends CrudRepository<UserAction, Long> {
}
