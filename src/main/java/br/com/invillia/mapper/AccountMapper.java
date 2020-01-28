package br.com.invillia.mapper;

import br.com.invillia.domain.Account;
import br.com.invillia.domain.response.AccountResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper {

    public List<AccountResponse> listToListAccountResponse(List<Account> accounts) {

        List<AccountResponse> accountResponses = new ArrayList<>();

        for( Account account : accounts){
            AccountResponse accountResponse = new AccountResponse();

            accountResponse.setId(account.getId());
            accountResponse.setAccount(account.getAccount());
            accountResponse.setAgency(account.getAgency());

            accountResponses.add(accountResponse);
        }

        return accountResponses;
    }
}

