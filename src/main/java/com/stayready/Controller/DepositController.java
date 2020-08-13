package com.stayready.Controller;

import com.stayready.domains.Deposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.stayready.Repositories.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class DepositController {

    private DepositRepository depositRepository;

    @Autowired
    public DepositController(DepositController depositController){
        this.depositRepository = depositRepository;
    }

    @RequestMapping(value="/deposits", method = RequestMethod.GET)
    public Iterable<Deposit> getAllDeposits(){
        return depositRepository.findAll();
    }

    @RequestMapping(value="/deposits/{depositId}", method=RequestMethod.GET)
    public ResponseEntity<?> getDeposit(@PathVariable Long id) {
        Deposit d = depositRepository.findOne(id);
        return new ResponseEntity<> (d, HttpStatus.OK);
    }

    @RequestMapping(value="/deposits/{depositId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateDeposit(@RequestBody Deposit deposit, @PathVariable Long id) {
        // Save the entity
        Deposit d = depositRepository.save(deposit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/deposit/{depositId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteDeposit(@PathVariable Long id) {
        depositRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deposit/{depositId}/", method = RequestMethod.POST)
    public ResponseEntity<?> createDeposit(@PathVariable Long pollId, @RequestBody Deposit
            deposit) {
        deposit = depositRepository.save(deposit);
        // Set the headers for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(deposit.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }



}
