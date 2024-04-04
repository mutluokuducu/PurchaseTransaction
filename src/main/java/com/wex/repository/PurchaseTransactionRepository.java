package com.wex.repository;

import com.wex.entity.PurchaseTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseTransactionRepository extends JpaRepository<PurchaseTransaction, Long> {

}
