package mph.pojo;
import mph.annotation.FixedFieldAnnotations;

import java.time.LocalDate;
public record AccountRecords(

                              @FixedFieldAnnotations(length = 9, format = "\\d{9}")
                              Integer customerId,

                              @FixedFieldAnnotations(length = 9, format = "\\d{9}")
                              Integer accountNumber,

                              @FixedFieldAnnotations(length = 10, allowedValues = "Savings,Current,FD,NRE")
                              String accountType,

                              @FixedFieldAnnotations(length = 10, allowedValues = "Active,Inactive,Closed")
                              String accountStatus,

                              @FixedFieldAnnotations(length = 10,pattern = "dd-MM-yyyy")
                              LocalDate openingDate,

                              @FixedFieldAnnotations(length = 3, allowedValues = "USD,EUR,INR,GBP,JPY")
                              String currencyCode,

                              @FixedFieldAnnotations(length = 6, format = "\\d{6}")
                              String branchCode,

                              @FixedFieldAnnotations(length = 11)
                              String ifscCode,

                              @FixedFieldAnnotations(length = 14, format = "\\d{11}\\.\\d{2}")
                              Double currentBalance,

                              @FixedFieldAnnotations(length = 14, format = "\\d{11}(\\.\\d{2})")
                              Double availableBalance,

                              @FixedFieldAnnotations(length = 12, format = "\\d{9}(\\.\\d{2})")
                              Double minimumBalance,

                              @FixedFieldAnnotations(length = 10, pattern = "dd-MM-yyyy")
                              LocalDate lastTransactionDate,

                              @FixedFieldAnnotations(length = 2, format = "\\d{1,2}")
                              Integer interestRate,

                              @FixedFieldAnnotations(length = 10, allowedValues = "YES,NO")
                              String autoDebitEnabled) {
}
