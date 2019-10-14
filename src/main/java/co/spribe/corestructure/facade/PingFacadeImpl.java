package co.spribe.corestructure.facade;

import co.spribe.corestructure.dto.PingDto;
import co.spribe.corestructure.dto.PingResponseDto;
import co.spribe.corestructure.dto.mapper.PingMapper;
import co.spribe.corestructure.exception.PingNotAllowedException;
import co.spribe.corestructure.exception.UserNotFoundException;
import co.spribe.corestructure.model.Ping;
import co.spribe.corestructure.model.User;
import co.spribe.corestructure.service.PingService;
import co.spribe.corestructure.service.UserBlackListService;
import co.spribe.corestructure.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PingFacadeImpl implements PingFacade {

    private UserService userService;
    private PingService pingService;
    private UserBlackListService userBlackListService;
    private PingMapper pingMapper;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setPingService(PingService pingService) {
        this.pingService = pingService;
    }

    @Autowired
    public void setUserBlackListService(UserBlackListService userBlackListService) {
        this.userBlackListService = userBlackListService;
    }

    @Autowired
    public void setPingMapper(PingMapper pingMapper) {
        this.pingMapper = pingMapper;
    }

    @Override
    public PingResponseDto ping(PingDto pingDto) throws PingNotAllowedException, UserNotFoundException {
        String userName = pingDto.getUserName();
        Optional<User> userByLogin = userService.getUserByLogin(userName);

        if (!userByLogin.isPresent()) {
            throw new UserNotFoundException();
        }

        User user = userByLogin.get();

        boolean userExistInBlackList = userBlackListService.doesExist(user);
        if (userExistInBlackList) {
            throw new PingNotAllowedException();
        }

        Ping ping = pingMapper.pingDtoToPing(pingDto);
        ping.setUser(user);

        Ping updatedPing = pingService.addPing(ping);

        return null;
    }
}
