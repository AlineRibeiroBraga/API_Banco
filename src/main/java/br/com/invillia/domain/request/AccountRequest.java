package br.com.invillia.domain.request;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class AccountRequest {

    @NotNull(message = "white field!")
    private String CPF;
}