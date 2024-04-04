package com.wex.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "purchase_transactions")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseTransaction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 50, nullable = false)
  private String description;

  @Column(nullable = false)
  private LocalDate transactionDate;

  @Column(nullable = false)
  private BigDecimal purchaseAmount;
}
