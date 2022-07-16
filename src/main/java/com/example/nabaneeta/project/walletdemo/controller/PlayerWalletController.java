package com.example.nabaneeta.project.walletdemo.controller;

import com.example.nabaneeta.project.walletdemo.modelentity.PlayerWallet;
import com.example.nabaneeta.project.walletdemo.service.PlayerWalletService;
import com.example.nabaneeta.project.walletdemo.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PlayerWalletController {

    @Autowired
    private PlayerWalletService playerWalletService;

    @Autowired
    private ValidationErrorService validationErrorService;

    @GetMapping("/playerwallet")
    public ResponseEntity<?> getAll(){
        return  new ResponseEntity<>(playerWalletService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/playerwallet/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return  new ResponseEntity<>(playerWalletService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/playerwallet")
    public ResponseEntity<?> create(@Validated @RequestBody PlayerWallet playerWallet, BindingResult bindingResult)
    {
        ResponseEntity errors= validationErrorService.validate(bindingResult);
        if(errors!=null) {
            return errors;
        }
        else {
            PlayerWallet playerWallet1=playerWalletService.createOrUpdate(playerWallet);
            return new ResponseEntity<PlayerWallet>(playerWallet1, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/playerwallet/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        playerWalletService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }




}
