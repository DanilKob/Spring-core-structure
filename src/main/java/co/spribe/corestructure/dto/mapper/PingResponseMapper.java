package co.spribe.corestructure.dto.mapper;

import co.spribe.corestructure.dto.PingResponseDto;
import co.spribe.corestructure.ping.model.Ping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PingResponseMapper {
    @Mapping(target = "userName", source = "user.login")
    @Mapping(target = "pingId", source = "id")
    PingResponseDto pingToPingResponseDto(Ping ping);
}
