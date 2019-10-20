package co.spribe.corestructure.ping.repository;

import co.spribe.corestructure.ping.model.Ping;
import org.springframework.data.repository.CrudRepository;

public interface PingRepository extends CrudRepository<Ping, Long> {
}
