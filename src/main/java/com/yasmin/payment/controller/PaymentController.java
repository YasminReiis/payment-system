package com.yasmin.payment.controller;

import com.yasmin.payment.dto.TransferRequest;
import com.yasmin.payment.model.Transaction;
import com.yasmin.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/transfer")
    public Transaction transfer(@RequestBody TransferRequest request) {
        return paymentService.transfer(request);
    }
}
