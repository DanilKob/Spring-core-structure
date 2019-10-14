package co.spribe.corestructure.controller;

import co.spribe.corestructure.dto.PingDto;
import co.spribe.corestructure.dto.PingResponseDto;
import co.spribe.corestructure.exception.PingNotAllowedException;
import co.spribe.corestructure.exception.UserNotFoundException;
import co.spribe.corestructure.facade.PingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class PingController {

    private PingFacade pingFacade;

    @Autowired
    public void setPingFacade(PingFacade pingFacade) {
        this.pingFacade = pingFacade;
    }

    @RequestMapping(path = "/ping")
    public ResponseEntity<PingResponseDto> ping(@RequestBody PingDto pingDto) {
        try {
            PingResponseDto pingResponseDto = pingFacade.ping(pingDto);
            ResponseEntity<PingResponseDto> pingResponseEntity = new ResponseEntity<>(pingResponseDto, HttpStatus.OK);
            return pingResponseEntity;
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (PingNotAllowedException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
