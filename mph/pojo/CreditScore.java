package mph.pojo;


import mph.annotation.FixedFieldAnnotations;
import java.time.LocalDate;

public record CreditScore(
        @FixedFieldAnnotations(length = 9, format = "\\d{9}")
        Long customerId,

        @FixedFieldAnnotations(length = 30, pattern = "^[A-Za-z\\s]+$")
        String fullName,

        @FixedFieldAnnotations(length = 10, pattern = "dd-MM-yyyy")
        LocalDate dateOfBirth,

        @FixedFieldAnnotations(length = 6, allowedValues = "MALE,FEMALE")
        String gender,

        @FixedFieldAnnotations(length = 10, format = "[A-Z0-9]{10}")
        String panNumber,

        @FixedFieldAnnotations(length = 12, format = "\\d{12}")
        Long aadharNumber,

        @FixedFieldAnnotations(length = 10, format = "\\d{10}")
        Long mobileNumber,

        @FixedFieldAnnotations(length = 30, format = ".+@.+\\..+")
        String emailAddress,

        @FixedFieldAnnotations(length = 11, format = "\\d{9}\\.\\d{1}")
        Double loanAmount,

        @FixedFieldAnnotations(length = 10, allowedValues = "HOME,PERSONAL,AUTO,EDUCATION")
        String loanCategory,

        @FixedFieldAnnotations(length = 3, format = "\\d{3}")
        Integer creditScore,

        @FixedFieldAnnotations(length = 10,pattern = "dd-MM-yyyy")
        LocalDate scoreDate,

        @FixedFieldAnnotations(length = 10, allowedValues = "CIBIL,EXPERIAN,EQUIFAX")
        String creditAgency
) {}

