package com.example.nabaneeta.project.walletdemo.modelentity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.bridge.IMessage;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PlayerTransaction")
public class PlayerTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id", nullable = false)
    private Long id;

    @NonNull
    @Column(name = "transactionType")
    private String transactionType;

    @NonNull
    @Column(name = "transactionBalance")
    private Double transactionBalance;

    @Column(name = "transactionId", unique = true)
    private String transactionId;


    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "transactionDate")
    private Date transactionDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "playerId", nullable = false)
    @JsonIgnore
    private PlayerWallet playerId;


    @PrePersist
    public void setTransactionIdAndTransactionDate() {
        this.transactionId= UUID.randomUUID().toString();
        this.transactionDate= new Date();
    }

    public static Date getDateWithoutTimeUsingFormat()
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd");
        return formatter.parse(formatter.format(new Date()));
    }

}
