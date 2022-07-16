package com.example.nabaneeta.project.walletdemo.repo;

import com.example.nabaneeta.project.walletdemo.modelentity.PlayerTransaction;
import com.example.nabaneeta.project.walletdemo.modelentity.PlayerWallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
//@EnableJpaRepositories
public interface PlayerTransactionRepository extends CrudRepository<PlayerTransaction, Long> {

//   Optional<PlayerTransaction> findByTransactionType(String st);

   List<PlayerTransaction> findByPlayerId(PlayerWallet playerWallet);


}
