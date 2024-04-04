package com.wex.service;

import static com.wex.constant.Constants.BASE_URL;
import static com.wex.constant.Constants.RATES_OF_EXCHANGE;

import com.wex.entity.PurchaseTransaction;
import com.wex.model.ConvertedTransactionDTO;
import com.wex.model.ExchangeRateData;
import com.wex.model.ExchangeRateResponse;
import com.wex.repository.PurchaseTransactionRepository;
import com.wex.utility.CurrencyUtils;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class CurrencyConversionService {

  private final WebClient webClient;
  private final PurchaseTransactionRepository purchaseTransactionRepository;

  @Autowired
  public CurrencyConversionService(Builder webClientBuilder,
      PurchaseTransactionRepository purchaseTransactionRepository) {
    this.webClient = webClientBuilder.baseUrl(BASE_URL).build();
    this.purchaseTransactionRepository = purchaseTransactionRepository;
  }

  public Optional<ConvertedTransactionDTO> getConvertedTransaction(Long id, String country) {
    Optional<PurchaseTransaction> transactionOptional = purchaseTransactionRepository.findById(id);

    if (transactionOptional.isEmpty()) {
      return Optional.empty();
    }

    PurchaseTransaction transaction = transactionOptional.get();
    try {
      ExchangeRateData exchangeRate = getExchangeRate(country,
          transaction.getTransactionDate()).block();
      Objects.requireNonNull(exchangeRate, "Exchange rate cannot be null");

      BigDecimal convertedAmount = CurrencyUtils.convert(
          transaction.getPurchaseAmount(), exchangeRate.getExchangeRate());

      ConvertedTransactionDTO convertedTransactionDTO = ConvertedTransactionDTO.builder()
          .id(id)
          .description(transaction.getDescription())
          .country(exchangeRate.getCountry())
          .transactionDate(transaction.getTransactionDate())
          .originalAmount(transaction.getPurchaseAmount())
          .currencyCode(exchangeRate.getCurrency())
          .exchangeRate(exchangeRate.getExchangeRate())
          .convertedAmount(convertedAmount)
          .build();

      return Optional.of(convertedTransactionDTO);
    } catch (Exception e) {
      log.error("Error during currency conversion: {}", e.getMessage());
      return Optional.empty();
    }
  }

  private Mono<ExchangeRateData> getExchangeRate(String country, LocalDate transactionDate) {
    LocalDate sixMonthsAgo = transactionDate.minusMonths(6);
    String uri = buildExchangeRateUri(country, sixMonthsAgo);

    log.info("Final URI: {}", uri);

    return webClient.get()
        .uri(uri)
        .retrieve()
        .bodyToMono(ExchangeRateResponse.class)
        .flatMapMany(response -> Mono.justOrEmpty(response.getData()))
        .flatMapIterable(data -> data)
        .sort(Comparator.comparing(ExchangeRateData::getRecordDate).reversed())
        .next()
        .switchIfEmpty(Mono.error(new IllegalStateException(
            "No conversion rate available within the last 6 months for " + country)));
  }

  private String buildExchangeRateUri(String country, LocalDate sixMonthsAgo) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return UriComponentsBuilder.fromPath(RATES_OF_EXCHANGE)
        .queryParam("filter",
            "record_date:gte:" + sixMonthsAgo.format(formatter) + ",country:eq:" + country)
        .queryParam("fields", "country,currency,country_currency_desc,exchange_rate,record_date")
        .toUriString();
  }

}
