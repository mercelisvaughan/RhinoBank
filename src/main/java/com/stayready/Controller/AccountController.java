package com.stayready.Controller;


import com.stayready.Repositories.AccountRepository;
import com.stayready.domains.Account;
import com.stayready.domains.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AccountController {

    private AccountRepository accountRepository;

    @Autowired
    public AccountController(AccountController accountController){
        this.accountRepository = accountRepository;
    }

    @RequestMapping(value="/account", method = RequestMethod.GET)
    public Iterable<Account> getAllAccount(){
        return accountRepository.findAll();
    }

    @RequestMapping(value="/account/{accountId}", method=RequestMethod.GET)
    public ResponseEntity<?> getAccount(@PathVariable Long id) {
        Account a = accountRepository.findOne(id);
        return new ResponseEntity<> (a, HttpStatus.OK);
    }

    @RequestMapping(value="/account/{accountId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateAccount(@RequestBody Account account, @PathVariable Long id) {
        // Save the entity
        Account a = accountRepository.save(account);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/account/{accountId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        accountRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/account/{accountId}/", method = RequestMethod.POST)
    public ResponseEntity<?> createAccount(@PathVariable Long id, @RequestBody Account account) {
        account = accountRepository.save(account);
        // Set the headers for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(account.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    protected void verifyAccount(Long id) throws ResourceNotFoundException {
        Account account = accountRepository.findOne(id);
    }


}
