package co.spribe.corestructure.dto.mapper;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MapperBeans {

    @Bean
    public PingMapper pingMapper() {
        return Mappers.getMapper(PingMapper.class);
    }
}
