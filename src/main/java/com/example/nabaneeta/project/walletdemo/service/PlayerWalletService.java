package com.example.nabaneeta.project.walletdemo.service;

import com.example.nabaneeta.project.walletdemo.exception.PlayerWalletException;
import com.example.nabaneeta.project.walletdemo.modelentity.PlayerWallet;
import com.example.nabaneeta.project.walletdemo.repo.PlayerWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerWalletService {
    @Autowired
    private PlayerWalletRepository playerWalletRepository;

    public List<PlayerWallet> getAll(){
        return (List<PlayerWallet>) playerWalletRepository.findAll();
    }

    public PlayerWallet getById(Long id){
        Optional<PlayerWallet> playerWallet=playerWalletRepository.findById(id);
        if(playerWallet.isPresent()){
            return playerWallet.get();
        }
        throw new PlayerWalletException("Player "+id+" does not exists!");
    }

    public PlayerWallet createOrUpdate(PlayerWallet playerWallet){
        if(playerWallet.getId()==null){
            playerWalletRepository.save(playerWallet);
        }else {
            playerWalletRepository.save(playerWallet);
        }
        return playerWallet;
    }

    public boolean delete(Long id){
        Optional<PlayerWallet> playerWallet=playerWalletRepository.findById(id);
        if(playerWallet.isPresent()){
            playerWalletRepository.delete(playerWallet.get());
            return true;
        }
        throw new PlayerWalletException("Player "+id+" does not exists!");
    }

}
