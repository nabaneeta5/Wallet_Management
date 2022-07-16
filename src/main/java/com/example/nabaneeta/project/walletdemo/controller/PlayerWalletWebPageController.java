package com.example.nabaneeta.project.walletdemo.controller;

import com.example.nabaneeta.project.walletdemo.modelentity.PlayerTransaction;
import com.example.nabaneeta.project.walletdemo.modelentity.PlayerWallet;
import com.example.nabaneeta.project.walletdemo.repo.PlayerWalletRepository;
import com.example.nabaneeta.project.walletdemo.service.PlayerWalletService;
import com.example.nabaneeta.project.walletdemo.service.PlayerWalletWebPageService;
import com.example.nabaneeta.project.walletdemo.service.ValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class PlayerWalletWebPageController {

    @Autowired
    private PlayerWalletWebPageService playerWalletWebPageService;

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession){
        return playerWalletWebPageService.index(model, httpSession);
    }


    @GetMapping ("/home")
    public String checkUserLogin(Model model,HttpSession httpSession){
        return playerWalletWebPageService.checkUserLogin(httpSession);
    }

    @PostMapping("/home")
    public String userLogin(Model model,@ModelAttribute ("playerWalletObject") PlayerWallet playerWalletObject, HttpServletRequest request){
        return playerWalletWebPageService.userLogin(playerWalletObject, request);
    }

    @GetMapping ("/transaction")
    public String transaction(Model model, HttpSession httpSession){
        return playerWalletWebPageService.transaction(model, httpSession);
    }

    @PostMapping("/transaction")
    public String userTransaction(Model model,@ModelAttribute ("playerTransactionObject") PlayerTransaction playerTransactionObject, BindingResult result , HttpServletRequest request){
        return playerWalletWebPageService.userTransaction(model, playerTransactionObject, result, request);
//        return  null;
    }


    @GetMapping ("/logout")
    public String userLogout(HttpSession httpSession){
        return playerWalletWebPageService.userLogout(httpSession);
    }

}
