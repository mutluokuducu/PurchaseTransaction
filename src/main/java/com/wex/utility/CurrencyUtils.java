package com.wex.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class CurrencyUtils {

  private CurrencyUtils() {
  }

  public static BigDecimal convert(BigDecimal amount, BigDecimal exchangeRate) {
    return amount.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);
  }

}
