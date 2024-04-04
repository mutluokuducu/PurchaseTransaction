package com.wex.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ConvertedTransactionDTO {

  private Long id;
  private String description;
  private String country;
  private LocalDate transactionDate;
  private BigDecimal originalAmount;
  private String currencyCode;
  private BigDecimal exchangeRate;
  private BigDecimal convertedAmount;

}
