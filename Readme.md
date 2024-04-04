# Purchase Transaction Application

## Description

This application is designed to store and retrieve purchase transactions, supporting currency conversion based on the Treasury Reporting Rates of Exchange API. It is built with robustness and production-readiness in mind, including comprehensive functional testing.

## Features

- **Store Purchase Transactions**: Accepts and stores details of purchase transactions, including a description, transaction date, and purchase amount in USD.
- **Retrieve and Convert Transactions**: Retrieves stored transactions and converts the purchase amount to a specified country's currency using the latest exchange rates.

## Requirements

- Java 11 or later for Gateways implementation.
- Alternatively, GoLang for TAG implementation.
- Access to the internet for fetching currency exchange rates from the [Treasury Reporting Rates of Exchange API](https://fiscaldata.treasury.gov/datasets/treasury-reporting-rates-exchange/treasury-reporting-rates-of-exchange).


## Field Specifications

- **Description**: Up to 50 characters.
- **Transaction Date**: Must be in a valid date format.
- **Purchase Amount**: A positive amount rounded to the nearest cent.
- **Unique Identifier**: Automatically assigned to uniquely identify each purchase.

## Currency Conversion

- The conversion uses rates from the last 6 months, matching or preceding the purchase date.
- An error is returned if no rate is available for the target currency within this timeframe.
- Amounts are converted and rounded to two decimal places.

### Configuration

1. Ensure Java 11 (or later) or GoLang is installed on your system.
2. Clone this repository to your local machine.
3. Configure environment variables as required (list any necessary variables and their descriptions).


## Running the Application

To run the application, execute the following command:
```
mvn clean install
```
### API Documentation

The REST API endpoints are documented using Swagger. Access the documentation at:
[Swagger UI](http://localhost:9090/swagger-ui/index.html)

### Testing

This project uses JUnit and Mockito for unit testing.
Tests are designed to ensure the accuracy and efficiency of the word counting logic,
including its interaction with external services like the Translator API.

To run the tests, use the following Maven command:
```
mvn test
```