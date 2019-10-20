package co.spribe.corestructure.service.impl;

import co.spribe.corestructure.ping.model.Ping;
import co.spribe.corestructure.ping.repository.PingRepository;
import co.spribe.corestructure.service.PingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PingServiceImpl implements PingService {

    private PingRepository pingRepository;

    @Autowired
    public void setPingRepository(PingRepository pingRepository) {
        this.pingRepository = pingRepository;
    }

    @Override
    public Ping addPing(Ping ping) {
        return pingRepository.save(ping);
    }
}
