package ObjectFactory;

import com.wex.entity.PurchaseTransaction;
import com.wex.model.PurchaseTransactionDTO;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ObjectFactory {

  public static PurchaseTransaction buildPurchaseTransaction() {
    return PurchaseTransaction.builder()
        .id(1L)
        .description("Sample Transaction")
        .purchaseAmount(BigDecimal.valueOf(999.99))
        .transactionDate(LocalDate.of(2024, 4, 1))
        .build();
  }

  public static PurchaseTransactionDTO buildPurchaseTransactionDto() {
    return PurchaseTransactionDTO.builder()
        .description("Sample Transaction")
        .purchaseAmount(BigDecimal.valueOf(999.99))
        .transactionDate(LocalDate.of(2024, 4, 1))
        .build();
  }

}
