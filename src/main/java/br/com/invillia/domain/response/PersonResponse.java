package br.com.invillia.domain.response;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class PersonResponse {

    @NotBlank
    private long id;

    @NotBlank
    private String name;
}
