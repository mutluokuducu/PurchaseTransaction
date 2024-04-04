package com.wex.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ObjectFactory.ObjectFactory;
import com.wex.entity.PurchaseTransaction;
import com.wex.model.PurchaseTransactionDTO;
import com.wex.repository.PurchaseTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PurchaseTransactionServiceTest {

  @Mock
  private PurchaseTransactionRepository purchaseTransactionRepository;

  @InjectMocks
  private PurchaseTransactionService purchaseTransactionService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void saveTransaction_ShouldSaveTransactionCorrectly() {
    // Arrange

    PurchaseTransaction savedTransaction = ObjectFactory.buildPurchaseTransaction();
    PurchaseTransactionDTO dto = ObjectFactory.buildPurchaseTransactionDto();
    when(purchaseTransactionRepository.save(any(PurchaseTransaction.class))).thenReturn(
        savedTransaction);
    // Act
    PurchaseTransaction result = purchaseTransactionService.saveTransaction(dto);

    // Assert
    verify(purchaseTransactionRepository).save(any(PurchaseTransaction.class));
    assert savedTransaction.getDescription().equals(result.getDescription());
    assert savedTransaction.getPurchaseAmount().equals(result.getPurchaseAmount());
    assert savedTransaction.getTransactionDate().equals(result.getTransactionDate());
  }

}
