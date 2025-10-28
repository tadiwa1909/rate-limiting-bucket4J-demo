package com.example.rate_limiting_demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Data
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue
    @Column(updatable = false)
    private Long id;

    @Column(name = "account_number", unique = true, updatable = false)
    private String accountNo;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "balance", updatable = false)
    private long balance;

    @Column(name = "date_opened", updatable = false)
    private LocalDateTime dateOpened;

    @ManyToOne
    private User user;

    public Account(String accountNo, Currency currency, long balance, LocalDateTime dateOpened) {
        this.accountNo = accountNo;
        this.currency = currency;
        this.balance = balance;
        this.dateOpened = dateOpened;
    }
}
