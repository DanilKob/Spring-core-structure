package co.spribe.corestructure.repository;

import co.spribe.corestructure.model.UserBlackList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBlackListRepository extends CrudRepository<UserBlackList, Long> {

}
