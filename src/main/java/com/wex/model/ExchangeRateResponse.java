package com.wex.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeRateResponse {

  private List<ExchangeRateData> data;

}