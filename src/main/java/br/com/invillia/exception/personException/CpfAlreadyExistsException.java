package br.com.invillia.exception.personException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CpfAlreadyExistsException extends RuntimeException{

    public CpfAlreadyExistsException(String title) {
        super(title);
    }
}

