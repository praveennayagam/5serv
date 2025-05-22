package mph.pojo;

import mph.annotation.FixedFieldAnnotations;

import java.time.LocalDateTime;

public record BankFundTransfer(
                               @FixedFieldAnnotations(length = 12, format = "\\d{12}")
                               String transactionId,
                               @FixedFieldAnnotations(length = 15, format = "\\d{15}")
                               Long senderAccountNumber,
                               @FixedFieldAnnotations(length = 15, format = "\\d{15}")
                               Long receiverAccountNumber,
                               @FixedFieldAnnotations(length = 15, format = "\\d{12}\\.\\d{2}")
                               Double transactionAmount,
                               @FixedFieldAnnotations(length = 19, pattern = "dd-MM-yyyy HH:mm:ss")
                               LocalDateTime transactionDateTime,
                               @FixedFieldAnnotations(length = 3, allowedValues = "USD,INR,EUR,GBP")
                               String currencyCode,
                               @FixedFieldAnnotations(length = 7, allowedValues = "NEFT,RTGS,IMPS,SWIFT")
                               String transactionType,
                               @FixedFieldAnnotations(length = 11)
                               String bankCode,
                               @FixedFieldAnnotations(length = 10, allowedValues = "Success,Pending,Failure")
                               String transactionStatus,
                               @FixedFieldAnnotations(length = 16)
                               String referenceNumber) {
}
