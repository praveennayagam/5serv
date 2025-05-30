package pojo;

import annotation.FixedFieldAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

public record InterestRate(
                           @FixedFieldAnnotations(length = 9, format = "\\d{9}")
                           Long customerId,
                           @FixedFieldAnnotations(length = 12, format = "\\d{12}")
                           Long accountNumber,
                           @FixedFieldAnnotations(length = 3, allowedValues = "USD,INR,EUR")
                           String currencyCode,
                           @FixedFieldAnnotations(length = 6, format = "\\d{3}\\.\\d{2}")
                           Double interestRate,
                           @FixedFieldAnnotations(length = 10, allowedValues = "FIXED,VARIABLE")
                           String rateType,
                           @FixedFieldAnnotations(length = 6)
                           String bankCode,
                           @FixedFieldAnnotations(length = 10, allowedValues = "ACTIVE,INACTIVE")
                           String accountStatus,
                           @FixedFieldAnnotations(length = 10, pattern = "dd-MM-yyyy")
                           LocalDate lastUpdate,
                           @FixedFieldAnnotations(length = 12, format = "\\d{9}\\.\\d{2}")
                           BigDecimal principalAmount,
                           @FixedFieldAnnotations(length = 12, format = "\\d{9}\\.\\d{2}")
                           BigDecimal balance,
                           @FixedFieldAnnotations(length = 12, format = "\\d{9}\\.\\d{2}")
                           BigDecimal minimumDue) {
}
