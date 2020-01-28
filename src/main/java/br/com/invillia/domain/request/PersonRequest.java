package br.com.invillia.domain.request;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class PersonRequest {

    @NotBlank(message = "white field!")
    private String name;

    @NotBlank(message = "white field!")
    private String CPF;
}
