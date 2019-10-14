package co.spribe.corestructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PingDto {

    @JsonProperty(value = "userName")
    @NotBlank
    private String userName;

    @JsonProperty(value = "data")
    @NotBlank
    private String data;
}
