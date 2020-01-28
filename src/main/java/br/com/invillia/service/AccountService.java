package br.com.invillia.service;

import br.com.invillia.domain.Account;
import br.com.invillia.domain.Person;
import br.com.invillia.domain.request.AccountRequest;
import br.com.invillia.domain.request.WithDrawRequest;
import br.com.invillia.domain.response.AccountResponse;
import br.com.invillia.exception.accountException.AccountException;
import br.com.invillia.exception.accountException.AccountNotFoundException;
import br.com.invillia.exception.personException.PersonNotFoundException;
import br.com.invillia.mapper.AccountMapper;
import br.com.invillia.repository.AccountRepository;
import br.com.invillia.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class AccountService {

    final private AccountRepository accountRepository;

    final private AccountMapper accountMapper;

    final private PersonRepository personRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper, PersonRepository personRepository) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.personRepository = personRepository;
    }

    public List<AccountResponse> findAll(){

        List<Account> accounts = accountRepository.findAll();

        return accountMapper.listToListAccountResponse(accounts);
    }

    public long insert(@Valid AccountRequest accountRequest) {

        Person person = personRepository.findByCPF(accountRequest.getCPF());

        if(person == null){
            throw new PersonNotFoundException("Person don't found!");
        }

        Account account = new Account();
        account.setPerson(person);
        do{
            account.setAccount( (long)(Math.random()*99999)+10000);
            account.setAgency( (long)(Math.random()*9999) + 1000);
        }while(accountRepository.findByAccount(account.getAccount()) != null);

        account = accountRepository.save(account);

        return account.getId();
    }

    public void withDraw(Long idAccount, WithDrawRequest drawRequest) {

        Account account = accountRepository.findById(idAccount).get();

        if( account != null){

            double balance  = account.getBalance();
            double value = drawRequest.getBalance();

            if( value <= balance + Account.BOUND){
                balance -= value;
                account.setBalance(balance);
                accountRepository.save(account);
            }
            else{
                throw new AccountException("Unavailable value!");
            }
        }

    }

    public void deposit(long id, WithDrawRequest drawRequest) {

        Account account = accountRepository.findById(id).orElseThrow( () -> new AccountNotFoundException("Account don't found!"));

        if(drawRequest.getBalance() > 0){
            account.setBalance( account.getBalance() + drawRequest.getBalance());
        }
        else{
            throw new AccountException("Unavailable value!");
        }
    }

    public void delete(long id ){

        Account account  = accountRepository.findById(id).orElseThrow(() -> new AccountNotFoundException("Account don't found!"));

        accountRepository.delete(account);
    }
}