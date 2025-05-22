package mph.pojo;

import mph.annotation.FixedFieldAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public record BankTransaction(
        @FixedFieldAnnotations(length = 9, format = "\\d{9}")
        Long transactionId,

        @FixedFieldAnnotations(length = 10, pattern = "^[A-Za-z]+$")
        String customerName,

        @FixedFieldAnnotations(length = 10, format = "\\d{2}-\\d{2}-\\d{4}")
        LocalDate transactionDate,

        @FixedFieldAnnotations(length = 8,  pattern = "HH:mm:ss")
        LocalTime transactionTime,

        @FixedFieldAnnotations(length = 13, format = "\\d{10}\\.\\d{2}")
        BigDecimal accountBalance,

        @FixedFieldAnnotations(length = 3, allowedValues = "USD,EUR,INR,GBP,JPY")
        String currencyType,

        @FixedFieldAnnotations(length = 6, format = "\\d{6}")
        Long branchCode,

        @FixedFieldAnnotations(length = 8, allowedValues = "true,false")
        String transactionApprovalStatus,

        @FixedFieldAnnotations(length = 7, format = "\\d{4}\\.\\d{2}")
        Double transactionalAmount,

        @FixedFieldAnnotations(length = 4, format = "\\d{4}")
        Integer branchId) {
}
