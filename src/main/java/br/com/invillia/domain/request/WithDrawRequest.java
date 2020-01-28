package br.com.invillia.domain.request;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class WithDrawRequest {

    @NotNull(message = "white field!")
    private double balance;
}