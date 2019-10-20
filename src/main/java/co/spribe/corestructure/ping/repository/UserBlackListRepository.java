package co.spribe.corestructure.ping.repository;

import co.spribe.corestructure.ping.model.UserBlackList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBlackListRepository extends CrudRepository<UserBlackList, Long> {

}
