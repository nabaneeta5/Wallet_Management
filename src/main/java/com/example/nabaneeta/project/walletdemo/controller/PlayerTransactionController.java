package com.example.nabaneeta.project.walletdemo.controller;

import com.example.nabaneeta.project.walletdemo.exception.PlayerWalletException;
import com.example.nabaneeta.project.walletdemo.modelentity.PlayerTransaction;
import com.example.nabaneeta.project.walletdemo.modelentity.PlayerWallet;
import com.example.nabaneeta.project.walletdemo.service.PlayerTransactionService;
import com.example.nabaneeta.project.walletdemo.service.PlayerWalletService;
import com.example.nabaneeta.project.walletdemo.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class PlayerTransactionController {

    @Autowired
    private PlayerTransactionService playerTransactionService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @GetMapping("/playerwallet/{wallet_id}/transactions/")
    public ResponseEntity<?> getAll(@PathVariable Long wallet_id){
        return  new ResponseEntity<>(playerTransactionService.getAll(wallet_id), HttpStatus.OK);
    }

    @PostMapping("/playerwallet/{wallet_id}/transactions/")
    public ResponseEntity<?> create(@PathVariable Long wallet_id, @Validated @RequestBody PlayerTransaction playerTransaction, BindingResult bindingResult)
    {
        ResponseEntity errors= validationErrorService.validate(bindingResult);
        if(errors!=null) {
            return errors;
        }
        else {
            PlayerTransaction playerTransaction1=playerTransactionService.createOrUpdate(wallet_id, playerTransaction);
            return new ResponseEntity<PlayerTransaction>(playerTransaction1, HttpStatus.CREATED);
        }
    }

    @PostMapping("/playerwallet/{wallet_id}/credittrans/{transamount}")
    public ResponseEntity<?> credit_trans(@PathVariable Long wallet_id,@PathVariable Double transamount, @Validated @RequestBody PlayerTransaction playerTransaction, BindingResult bindingResult)
    {
        ResponseEntity errors= validationErrorService.validate(bindingResult);
        if(errors!=null) {
            return errors;
        }
        else {
            PlayerTransaction playerTransaction1=playerTransactionService.credit_trans(wallet_id, transamount,playerTransaction);
            return new ResponseEntity<PlayerTransaction>(playerTransaction1, HttpStatus.CREATED);
        }
    }

    @PostMapping("/playerwallet/{wallet_id}/debittrans/{transamount}")
    public ResponseEntity<?> debit_trans(@PathVariable Long wallet_id,@PathVariable Double transamount, @Validated @RequestBody PlayerTransaction playerTransaction, BindingResult bindingResult)
    {
        ResponseEntity errors= validationErrorService.validate(bindingResult);
        if(errors!=null) {
            return errors;
        }
        else {
            PlayerTransaction playerTransaction1=playerTransactionService.debit_trans(wallet_id, transamount,playerTransaction);
            return new ResponseEntity<PlayerTransaction>(playerTransaction1, HttpStatus.CREATED);
        }
    }

//    @DeleteMapping("/playerwallet/{id}")
//    public ResponseEntity<?> delete(@PathVariable Long id){
//        playerWalletService.delete(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }


}
