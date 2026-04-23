package com.yasmin.payment.model;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String fromAccount;
    private String toAccount;

    private BigDecimal amount;
    private String status;
    private LocalDateTime createdAt;
}
