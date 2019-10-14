package co.spribe.corestructure.facade;

import co.spribe.corestructure.dto.PingDto;
import co.spribe.corestructure.dto.PingResponseDto;
import co.spribe.corestructure.exception.PingNotAllowedException;
import co.spribe.corestructure.exception.UserNotFoundException;

public interface PingFacade {
    PingResponseDto ping(PingDto pingDto) throws UserNotFoundException, PingNotAllowedException;
}
