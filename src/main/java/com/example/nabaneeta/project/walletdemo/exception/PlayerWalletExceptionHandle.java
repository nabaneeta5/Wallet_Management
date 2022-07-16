package com.example.nabaneeta.project.walletdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class PlayerWalletExceptionHandle extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<?> handleWalletException(PlayerWalletException playerWalletException, WebRequest request){
        PlayerWalletExceptionEntity playerWalletExceptionEntity=new PlayerWalletExceptionEntity(playerWalletException.getMessage());
        return new ResponseEntity<PlayerWalletExceptionEntity>(playerWalletExceptionEntity, HttpStatus.BAD_REQUEST);
    }

}
