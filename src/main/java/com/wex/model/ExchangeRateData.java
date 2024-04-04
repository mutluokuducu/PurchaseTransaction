package com.wex.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateData {

  @JsonProperty("country")
  private String country;
  @JsonProperty("currency")
  private String currency;
  @JsonProperty("country_currency_desc")
  private String countryCurrencyDesc;
  @JsonProperty("exchange_rate")
  private BigDecimal exchangeRate;
  @JsonProperty("record_date")
  private LocalDate recordDate;

}