package br.com.invillia.domain.response;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class AccountResponse {

    @NotBlank
    private long id;

    @NotBlank
    private long account;

    @NotBlank
    private long agency;

    @NotBlank
    private double balance;
}
