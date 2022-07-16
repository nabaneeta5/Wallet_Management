package com.example.nabaneeta.project.walletdemo.modelentity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PlayerWallet")
public class PlayerWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "username", nullable = false, unique = true)
    private String userName;

    @Column(name = "currentbalance", nullable = false)
    private Double currentBalance;

    @Column(name = "transactions")
    @OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "playerId", orphanRemoval = true)
    @JsonIgnore
    private List<PlayerTransaction> playerTransactions;

    @PrePersist
    public void setBalance(){
        if(this.currentBalance==null){
            this.currentBalance= new Double(0);
        }
    }
}
