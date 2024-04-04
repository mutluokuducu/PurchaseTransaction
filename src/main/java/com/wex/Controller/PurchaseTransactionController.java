package com.wex.Controller;

import static com.wex.constant.Constants.API_TRANSACTIONS;

import com.wex.entity.PurchaseTransaction;
import com.wex.model.PurchaseTransactionDTO;
import com.wex.service.PurchaseTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseTransactionController {

  private final PurchaseTransactionService purchaseTransactionService;

  @Autowired
  public PurchaseTransactionController(PurchaseTransactionService purchaseTransactionService) {
    this.purchaseTransactionService = purchaseTransactionService;
  }

  @PostMapping(value = API_TRANSACTIONS)
  public ResponseEntity<PurchaseTransaction> createTransaction(
      @Validated @RequestBody PurchaseTransactionDTO transaction) {
    return ResponseEntity.ok(purchaseTransactionService.saveTransaction(transaction));
  }

}