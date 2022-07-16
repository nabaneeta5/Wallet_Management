package com.example.nabaneeta.project.walletdemo.service;

import com.example.nabaneeta.project.walletdemo.exception.PlayerWalletException;
import com.example.nabaneeta.project.walletdemo.modelentity.PlayerTransaction;
import com.example.nabaneeta.project.walletdemo.modelentity.PlayerWallet;
import com.example.nabaneeta.project.walletdemo.repo.PlayerTransactionRepository;
import com.example.nabaneeta.project.walletdemo.repo.PlayerWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerTransactionService {
    @Autowired
    private PlayerTransactionRepository playerTransactionRepository;

    @Autowired
    private  PlayerWalletRepository playerWalletRepository;

    public List<PlayerTransaction> getAll(Long wallet_id){
        Optional<PlayerWallet> playerWallet= playerWalletRepository.findById(wallet_id);
        if(playerWallet.isPresent()){
            return playerTransactionRepository.findByPlayerId(playerWallet.get());
        }
        throw new PlayerWalletException("Player "+wallet_id+" does not exists!");
    }

    public PlayerTransaction createOrUpdate(Long wallet_id, PlayerTransaction playerTransaction){
        Optional<PlayerWallet> playerWallet= playerWalletRepository.findById(wallet_id);
        if(playerWallet.isPresent()){
            String transType= playerTransaction.getTransactionType().toString();
            Double currBal= playerWallet.get().getCurrentBalance();
            Double transBal= playerTransaction.getTransactionBalance();
            if(transType.equals("credit") || transType.equals("debit")) {
                if (transType.equals("credit")) {
                    Double newCurrBal=Double.valueOf(String.valueOf(new BigDecimal(currBal + transBal).setScale(2, RoundingMode.HALF_EVEN)));
                    playerWallet.get().setCurrentBalance(newCurrBal);
                }
                else if (transType.equals("debit")) {
                    if (currBal < transBal) {
                        throw new PlayerWalletException("Transaction failed, current balance is low ");
                    }
                    Double newCurrBal=Double.valueOf(String.valueOf(new BigDecimal(currBal - transBal).setScale(2, RoundingMode.HALF_EVEN)));
                    playerWallet.get().setCurrentBalance(newCurrBal);
                }
            }else {
                throw new PlayerWalletException("Transaction type: debit / credit ");
            }
            playerTransaction.setPlayerId(playerWallet.get());
            playerTransactionRepository.save(playerTransaction);
            return playerTransaction;
        }
        throw new PlayerWalletException("Player "+wallet_id+" does not exists!");
    }

    public PlayerTransaction credit_trans(Long wallet_id, Double transamount, PlayerTransaction playerTransaction){
        Optional<PlayerWallet> playerWallet= playerWalletRepository.findById(wallet_id);
        if(playerWallet.isPresent()){
            Double currBal= playerWallet.get().getCurrentBalance();
            Double newCurrBal=Double.valueOf(String.valueOf(new BigDecimal(currBal + transamount).setScale(2, RoundingMode.HALF_EVEN)));
            playerWallet.get().setCurrentBalance(newCurrBal);
            playerTransaction.setTransactionType("credit");
            playerTransaction.setTransactionBalance(transamount);
            playerTransaction.setPlayerId(playerWallet.get());
            playerTransactionRepository.save(playerTransaction);
            return playerTransaction;
        }
        throw new PlayerWalletException("Player "+wallet_id+" does not exists!");
    }

    public PlayerTransaction debit_trans(Long wallet_id, Double transamount, PlayerTransaction playerTransaction){
        Optional<PlayerWallet> playerWallet= playerWalletRepository.findById(wallet_id);
        if(playerWallet.isPresent()){
            Double currBal= playerWallet.get().getCurrentBalance();
            if (currBal - transamount < new Double(0)) {
                throw new PlayerWalletException("Transaction failed, current balance is low ");
            }
            Double newCurrBal=Double.valueOf(String.valueOf(new BigDecimal(currBal - transamount).setScale(2, RoundingMode.HALF_EVEN)));
            playerWallet.get().setCurrentBalance(newCurrBal);
            playerTransaction.setTransactionType("debit");
            playerTransaction.setTransactionBalance(transamount);
            playerTransaction.setPlayerId(playerWallet.get());
            playerTransactionRepository.save(playerTransaction);
            return playerTransaction;
        }
        throw new PlayerWalletException("Player "+wallet_id+" does not exists!");
    }



//
//    public boolean delete(Long id){
//        Optional<PlayerWallet> playerWallet=playerWalletRepository.findById(id);
//        if(playerWallet.isPresent()){
//            playerWalletRepository.delete(playerWallet.get());
//            return true;
//        }
//        throw new PlayerWalletException("Player "+id+" does not exists!");
//    }


}
