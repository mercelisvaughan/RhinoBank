package com.stayready.Controller;

import com.stayready.Repositories.BillRepository;
import com.stayready.domains.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class BillController {

    private BillRepository billRepository;

    @Autowired
    public BillController(BillController billController){
        this.billRepository = billRepository;
    }

    @RequestMapping(value="/bills", method = RequestMethod.GET)
    public Iterable<Bill> getAllBills(){
        return billRepository.findAll();
    }

    @RequestMapping(value="/bill/{billId}", method=RequestMethod.GET)
    public ResponseEntity<?> getBill(@PathVariable Long id) {
        Bill b = billRepository.findOne(id);
        return new ResponseEntity<> (b, HttpStatus.OK);
    }

    @RequestMapping(value="/withdraw/{withdrawalId}", method=RequestMethod.PUT)
    public ResponseEntity<?> updateBill(@RequestBody Bill bill, @PathVariable Long id) {
        // Save the entity
        Bill b = billRepository.save(bill);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/bill/{billId}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteBill(@PathVariable Long id) {
        billRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/bill/{billId}/", method = RequestMethod.POST)
    public ResponseEntity<?> createBill(@PathVariable Long id, @RequestBody Bill
            bill) {
        bill = billRepository.save(bill);
        // Set the headers for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").buildAndExpand(bill.getId()).toUri());
        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

}
