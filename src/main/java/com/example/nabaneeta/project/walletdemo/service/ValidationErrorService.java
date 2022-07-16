package com.example.nabaneeta.project.walletdemo.service;

import com.example.nabaneeta.project.walletdemo.modelentity.PlayerWallet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationErrorService {

    public ResponseEntity<?> validate(BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            Map<String, String> errorMap =new HashMap<String, String>();
            for (FieldError error: bindingResult.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());

            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
        return  null;
    }
}
