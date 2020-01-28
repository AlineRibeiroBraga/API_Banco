package br.com.invillia.exception.accountException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountException extends RuntimeException {

    public AccountException(String title){
        super(title);
    }
}