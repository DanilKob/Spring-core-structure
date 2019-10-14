package co.spribe.corestructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PingResponseDto {

    @JsonProperty(value = "userName")
    private String userName;

    @JsonProperty(value = "pingId")
    private long pingId;
}
