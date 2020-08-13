package com.stayready.Controller;

import com.stayready.Repositories.WithdrawRepository;
import com.stayready.domains.Deposit;
import com.stayready.domains.Withdrawal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class WithdrawalController {

    private WithdrawRepository withdrawRepository;

    @Autowired
    public WithdrawalController(WithdrawalController withdrawalController){
        this.withdrawRepository = withdrawRepository;
    }


    @RequestMapping(value="/withdrawals", method = RequestMethod.GET)
    public Iterable<Withdrawal> getAllWithdrawals(){
        return withdrawRepository.findAll();
    }

    @RequestMapping(value="/withdraw/{withdrawId}", method=RequestMethod.GET)
    public ResponseEntity<?> getWithdrawal(@PathVariable Long id) {
        Withdrawal w = withdrawRepository.findOne(id);
        return new ResponseEntity<> (w, HttpStatus.OK);
    }

    @RequestMapping(value="/withdraw/{withdrawalId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateWithdrawal(@RequestBody Withdrawal withdraw, @PathVariable Long id) {
        // Save the entity
        Withdrawal w = withdrawRepository.save(withdraw);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/withdraw/{withdrawalId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteWithdrawal(@PathVariable Long id) {
        withdrawRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/withdraw/{withdrawalId}/", method = RequestMethod.POST)
    public ResponseEntity<?> createWithdraw(@PathVariable Long id, @RequestBody Withdrawal
            withdraw) {
        withdraw = withdrawRepository.save(withdraw);
        // Set the headers for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(withdraw.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }


}
