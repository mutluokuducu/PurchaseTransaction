package com.wex.Controller;

import static com.wex.constant.Constants.GET_CONVERT;

import com.wex.exception.ConversionException;
import com.wex.model.ConvertedTransactionDTO;
import com.wex.service.CurrencyConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {

  private final CurrencyConversionService currencyConversionService;

  @Autowired
  public CurrencyConversionController(CurrencyConversionService currencyConversionService) {
    this.currencyConversionService = currencyConversionService;
  }

  @GetMapping(value = GET_CONVERT)
  public ResponseEntity<ConvertedTransactionDTO> getConvertedTransaction(@PathVariable Long id,
      @RequestParam String country) {
    return currencyConversionService.getConvertedTransaction(id, country)
        .map(ResponseEntity::ok)
        .orElseThrow(() -> new ConversionException(
            "Conversion failed or transaction not found."));
  }
}
