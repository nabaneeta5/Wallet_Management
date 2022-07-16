package com.example.nabaneeta.project.walletdemo.service;

import com.example.nabaneeta.project.walletdemo.exception.PlayerWalletException;
import com.example.nabaneeta.project.walletdemo.modelentity.PlayerTransaction;
import com.example.nabaneeta.project.walletdemo.modelentity.PlayerWallet;
import com.example.nabaneeta.project.walletdemo.repo.PlayerTransactionRepository;
import com.example.nabaneeta.project.walletdemo.repo.PlayerWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerWalletWebPageService {
    @Autowired
    private PlayerWalletRepository playerWalletRepository;

    @Autowired
    private PlayerTransactionRepository playerTransactionRepository;


    public String index(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("playerWalletObject") != null) {
            return "redirect:/home";
        } else {
            model.addAttribute("playerWalletObject", new PlayerWallet());
            return "index";
        }

    }

    public String userLogin(PlayerWallet playerWalletObject, HttpServletRequest request) {
        Long id;
        String st = playerWalletObject.getUserName();
        Optional<PlayerWallet> playerWallet = playerWalletRepository.findByUserName(st);
        PlayerWallet playerWallet1 = new PlayerWallet();
        List<PlayerWallet> it = (List<PlayerWallet>) playerWalletRepository.findAll();
        if (it.isEmpty() || it == null) {
            id = new Long(1);
        } else {
            id = new Long(it.size() + 1);
        }
        if (!playerWallet.isPresent()) {
            playerWallet1.setId(id);
            playerWallet1.setUserName(st);
            playerWallet1.setCurrentBalance(new Double(0));
            playerWallet1.setPlayerTransactions(playerWalletObject.getPlayerTransactions());
            playerWalletRepository.save(playerWalletObject);
        } else {
            playerWallet1.setUserName(st);
            playerWallet1.setId(playerWallet.get().getId());
            playerWallet1.setCurrentBalance(playerWallet.get().getCurrentBalance());
            playerWallet1.setPlayerTransactions(playerWallet.get().getPlayerTransactions());

        }
        request.getSession().setAttribute("playerWalletObject", playerWallet1);
        request.getSession().setAttribute("id", playerWallet1.getId());
        return "redirect:/home";
    }

    public String checkUserLogin(HttpSession httpSession) {
        if (httpSession.getAttribute("playerWalletObject") == null) {
            return "redirect:/";
        } else {
            Optional<PlayerWallet> playerWallet = playerWalletRepository.findById((Long) httpSession.getAttribute("id"));
            PlayerWallet playerWallet1 = new PlayerWallet();
            playerWallet1.setId(playerWallet.get().getId());
            playerWallet1.setUserName(playerWallet.get().getUserName());
            playerWallet1.setCurrentBalance(playerWallet.get().getCurrentBalance());
            playerWallet1.setPlayerTransactions(playerWallet.get().getPlayerTransactions());
            httpSession.setAttribute("playerWalletObject", playerWallet1);
            return "home";
        }
    }

    public String transaction(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("playerWalletObject") == null) {
            return "redirect:/";
        } else {
            if (httpSession.getAttribute("err") != null) {
                model.addAttribute("err", httpSession.getAttribute("err").toString());
                httpSession.removeAttribute("err");
            }else {
                model.addAttribute("err", "");
            }
            model.addAttribute("playerTransactionObject", new PlayerTransaction());
            return "trans";
        }
    }

    public String userTransaction(Model model, PlayerTransaction playerTransactionObject,
                                  BindingResult result, HttpServletRequest request) {
        Double amt;
        if(result.hasErrors()){
            request.getSession().setAttribute("err","Enter Valid Amount");
        }
        try {
            if(playerTransactionObject.getTransactionBalance().isNaN() ){
                request.getSession().setAttribute("err","Enter Valid Amount");
            }else if(playerTransactionObject.getTransactionType()==null){
                request.getSession().setAttribute("err","Select Transaction Type");
            }else {
                PlayerWallet playerWallet= (PlayerWallet) request.getSession().getAttribute("playerWalletObject");
                if(playerTransactionObject.getTransactionType().equals("debit")){
                    if(playerWallet.getCurrentBalance()-playerTransactionObject.getTransactionBalance()<0){
                        request.getSession().setAttribute("err","Current Balance is "+playerWallet.getCurrentBalance()+" which is less than Transaction Amount");
                    }else {
                        amt= Double.valueOf(String.valueOf(new BigDecimal(playerWallet.getCurrentBalance()-playerTransactionObject.getTransactionBalance()).setScale(2, RoundingMode.HALF_EVEN)));
                        getTransact(playerTransactionObject, request, amt, playerWallet);
                    }
                }else if(playerTransactionObject.getTransactionType().equals("credit")){
                    amt= Double.valueOf(String.valueOf(new BigDecimal(playerWallet.getCurrentBalance()+playerTransactionObject.getTransactionBalance()).setScale(2, RoundingMode.HALF_EVEN)));
                    getTransact(playerTransactionObject, request, amt, playerWallet);
                }

            }
        }catch(Exception e) {
            request.getSession().setAttribute("err","Enter Valid Amount");
        }
        return "redirect:/transaction";

    }

    public void getTransact(PlayerTransaction playerTransactionObject, HttpServletRequest request, Double amt, PlayerWallet playerWallet) {
        playerWallet.setCurrentBalance(amt);
        PlayerTransaction playerTransaction=new PlayerTransaction();
        playerTransaction.setPlayerId(playerWallet);
        playerTransaction.setTransactionType(playerTransactionObject.getTransactionType());
        playerTransaction.setTransactionBalance(playerTransactionObject.getTransactionBalance());
        playerTransactionRepository.save(playerTransaction);
        playerWalletRepository.save(playerWallet);
        request.getSession().setAttribute("err","Transaction Successful");
    }

    public String userLogout(HttpSession httpSession) {
        httpSession.removeAttribute("playerWalletObject");
        httpSession.invalidate();
        return "redirect:/";
    }


}
