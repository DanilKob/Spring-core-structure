package co.spribe.corestructure.controller;

import co.spribe.corestructure.dto.PingDto;
import co.spribe.corestructure.dto.PingResponseDto;
import co.spribe.corestructure.exception.CustomException;
import co.spribe.corestructure.exception.PingNotAllowedException;
import co.spribe.corestructure.exception.UserNotFoundException;
import co.spribe.corestructure.exception.handler.ApiError;
import co.spribe.corestructure.exception.handler.ApiErrorBuilderFactory;
import co.spribe.corestructure.facade.PingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1")
public class PingController {

    private PingFacade pingFacade;
    private ApiErrorBuilderFactory apiErrorBuilderFactory;

    @Autowired
    public void setPingFacade(PingFacade pingFacade) {
        this.pingFacade = pingFacade;
    }

    @Autowired
    public void setApiErrorBuilderFactory(ApiErrorBuilderFactory apiErrorBuilderFactory) {
        this.apiErrorBuilderFactory = apiErrorBuilderFactory;
    }

    @RequestMapping(path = "/ping")
    public ResponseEntity<PingResponseDto> ping(@Valid @RequestBody PingDto pingDto) throws PingNotAllowedException {
        try {
            PingResponseDto pingResponseDto = pingFacade.ping(pingDto);
            ResponseEntity<PingResponseDto> pingResponseEntity = new ResponseEntity<>(pingResponseDto, HttpStatus.OK);
            return pingResponseEntity;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            ApiError apiError = apiErrorBuilderFactory.builder()
                    .setStatus(HttpStatus.BAD_REQUEST)
                    .setDebugMessage(e.getLocalizedMessage())
                    .setMessage("User not found")
                    .build();
            throw new CustomException(apiError);
        }
    }
}
