package co.spribe.corestructure.dto.mapper;

import co.spribe.corestructure.dto.PingDto;
import co.spribe.corestructure.model.Ping;
import org.mapstruct.Mapper;

@Mapper
public interface PingMapper {

    Ping pingDtoToPing(PingDto pingDto);
}
