package br.com.invillia.controller;

import br.com.invillia.domain.request.AccountRequest;
import br.com.invillia.domain.request.WithDrawRequest;
import br.com.invillia.domain.response.AccountResponse;
import br.com.invillia.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    final private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<AccountResponse> findAll(){

        return accountService.findAll();
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody @Valid AccountRequest accountRequest){

        long id = accountService.insert(accountRequest);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/account/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/withDraw/{id}")
    public ResponseEntity withDraw(@RequestBody WithDrawRequest drawRequest, @PathVariable final long id){

        accountService.withDraw(id,drawRequest);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/account/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @PostMapping("/deposit/{id}")
    public ResponseEntity deposit(@RequestBody WithDrawRequest drawRequest, @PathVariable long id){

        accountService.deposit(id,drawRequest);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/account/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete( @PathVariable long id){

        accountService.delete(id);

        final URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/account/{id}")
                .build(id);

        return ResponseEntity.created(location).build();
    }
}