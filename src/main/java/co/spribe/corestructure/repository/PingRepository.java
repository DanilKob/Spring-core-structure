package co.spribe.corestructure.repository;

import co.spribe.corestructure.model.Ping;
import org.springframework.data.repository.CrudRepository;

public interface PingRepository extends CrudRepository<Ping, Long> {
}
