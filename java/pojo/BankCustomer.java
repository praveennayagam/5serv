package pojo;
import annotation.FixedFieldAnnotations;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record BankCustomer(
                               @FixedFieldAnnotations(length = 5, format = "\\d{5}")
                               Integer customerId,

                               @FixedFieldAnnotations(length = 20, pattern = "^[A-Za-z\\s]+$")
                               String name,

                               @FixedFieldAnnotations(length = 10, allowedValues = "Male,Female,Others")
                               String gender,

                               @FixedFieldAnnotations(length = 10, pattern = "dd-MM-yyyy")
                               LocalDate dateofbirth,

                               @FixedFieldAnnotations(length = 10, format = "\\d{10}")
                               Long mobileNumber,

                               @FixedFieldAnnotations(length = 30, format = ".+@.+\\..+")
                               String email,

                               @FixedFieldAnnotations(length = 43)
                               Address address,

                               @FixedFieldAnnotations(length = 15, pattern = "^[A-Za-z]+$")
                               String nationality,

                               @FixedFieldAnnotations(length = 15, pattern = "^[A-Za-z]+$")
                               String occupation,

                               @FixedFieldAnnotations(length = 10, allowedValues = "Single,Married,Divorced,Widowed")
                               String maritalStatus,

                               @FixedFieldAnnotations(length = 19, pattern = "dd-MM-yyyy HH:mm:ss")
                               LocalDateTime registrationDateTime,

                               @FixedFieldAnnotations(length = 15)
                               String category,

                               @FixedFieldAnnotations(length = 12, pattern = "Low,Medium,High")
                               String incomeLevel,

                               @FixedFieldAnnotations(length = 3, format = "\\d{1,3}")
                               String creditScore,

                               @FixedFieldAnnotations(length = 15)
                               String bankService) {
}