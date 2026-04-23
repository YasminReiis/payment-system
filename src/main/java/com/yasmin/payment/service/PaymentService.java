package com.yasmin.payment.service;

import com.yasmin.payment.dto.TransferRequest;
import com.yasmin.payment.model.Account;
import com.yasmin.payment.model.Transaction;
import com.yasmin.payment.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final AccountRepository accountRepository;

    public Transaction transfer(TransferRequest request) {

        Account from = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Account to = accountRepository.findById(request.getToAccountId())
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        if (from.getBalance().compareTo(request.getAmount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        from.setBalance(from.getBalance().subtract(request.getAmount()));
        to.setBalance(to.getBalance().add(request.getAmount()));

        accountRepository.save(from);
        accountRepository.save(to);

        return Transaction.builder()
                .fromAccount(from.getId())
                .toAccount(to.getId())
                .amount(request.getAmount())
                .status("SUCCESS")
                .createdAt(LocalDateTime.now())
                .build();
    }
}
