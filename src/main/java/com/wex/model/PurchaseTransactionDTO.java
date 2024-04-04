package com.wex.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.validation.constraints.DecimalMin;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder(toBuilder = true)
public class PurchaseTransactionDTO {

  @Column(length = 50, nullable = false)
  private String description;

  @Column(nullable = false)
  private LocalDate transactionDate;

  @Column(nullable = false)
  @DecimalMin(value = "0.00", inclusive = false, message = "Purchase amount must be greater than 0")
  private BigDecimal purchaseAmount;

  public void setPurchaseAmount(PurchaseTransactionDTO transaction, BigDecimal amount) {
    BigDecimal roundedAmount = amount.setScale(2, RoundingMode.HALF_UP);
    transaction.setPurchaseAmount(roundedAmount);
  }
}
