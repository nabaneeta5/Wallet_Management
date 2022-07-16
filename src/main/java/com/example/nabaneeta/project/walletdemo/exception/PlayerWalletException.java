package com.example.nabaneeta.project.walletdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PlayerWalletException extends RuntimeException {
    public PlayerWalletException(String msg){
        super(msg);
    }
}
