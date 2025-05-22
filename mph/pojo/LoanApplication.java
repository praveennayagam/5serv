package mph.pojo;

import mph.annotation.FixedFieldAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

public record LoanApplication(
                              @FixedFieldAnnotations(length = 10, format = "\\d{10}")
                              Long applicationId,
                              @FixedFieldAnnotations(length = 40, pattern = "^[A-Za-z]+$")
                              String fullName,
                              @FixedFieldAnnotations(length = 9, format = "\\d{9}")
                              Long applicantIdNumber,
                              @FixedFieldAnnotations(length = 12, format = "\\d{9}\\.\\d{2}")
                              BigDecimal loanAmount,
                              @FixedFieldAnnotations(length = 12, format = "\\d{12}")
                              Long monthlyPayment,
                              @FixedFieldAnnotations(length = 3, format = "\\d{3}")
                              Integer loanTerm,
                              @FixedFieldAnnotations(length = 6, format = "\\d{3}\\.\\d{2}")
                              BigDecimal interestRate,
                              @FixedFieldAnnotations(length = 30, allowedValues = "Travel,Business,Home,Medical,Education")
                              String loanPurpose,
                              @FixedFieldAnnotations(length = 10, allowedValues = "PENDING,REJECTED,APPROVED")
                              String applicationStatus,
                              @FixedFieldAnnotations(length = 10, format = "\\d{2}-\\d{2}-\\d{4}")
                              LocalDate applicationDate,
                              @FixedFieldAnnotations(length = 5)
                              String branchCode) {
}
