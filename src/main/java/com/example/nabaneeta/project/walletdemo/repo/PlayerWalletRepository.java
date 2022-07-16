package com.example.nabaneeta.project.walletdemo.repo;

import com.example.nabaneeta.project.walletdemo.modelentity.PlayerWallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerWalletRepository extends CrudRepository<PlayerWallet, Long> {
    Optional<PlayerWallet> findByUserName(String st);

    Optional<PlayerWallet> findById(Long id);

}
