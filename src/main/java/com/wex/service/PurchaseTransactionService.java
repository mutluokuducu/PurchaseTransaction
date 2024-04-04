package com.wex.service;

import com.wex.entity.PurchaseTransaction;
import com.wex.model.PurchaseTransactionDTO;
import com.wex.repository.PurchaseTransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PurchaseTransactionService {

  private final PurchaseTransactionRepository purchaseTransactionRepository;

  @Autowired
  public PurchaseTransactionService(PurchaseTransactionRepository purchaseTransactionRepository) {
    this.purchaseTransactionRepository = purchaseTransactionRepository;
  }

  public PurchaseTransaction saveTransaction(PurchaseTransactionDTO transaction) {
    log.info("Transaction started");
    PurchaseTransaction purchaseTransaction = PurchaseTransaction.builder()
        .description(transaction.getDescription())
        .purchaseAmount(transaction.getPurchaseAmount())
        .transactionDate(transaction.getTransactionDate())
        .build();

    return purchaseTransactionRepository.save(purchaseTransaction);
  }
}
