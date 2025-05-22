package mph.pojo;
import mph.annotation.FixedFieldAnnotations;
import java.time.LocalDate;
public record BankKyc(@FixedFieldAnnotations(length = 15, pattern = "^[A-Za-z\\s]+$")
                      String fullName,

                      @FixedFieldAnnotations(length = 10, pattern = "dd-MM-yyyy")
                      LocalDate dateOfBirth,

                      @FixedFieldAnnotations(length = 15, pattern = "^[A-Za-z\\s]+$")
                      String state,

                      @FixedFieldAnnotations(length = 15, pattern = "^[A-Za-z\\s]+$")
                      String nationality,

                      @FixedFieldAnnotations(length = 10, format = "[A-Z0-9]{10}")
                      String panNumber,

                      @FixedFieldAnnotations(length = 15, allowedValues = "Passport,Driver License,Voter ID,Aadhaar")
                      String idProofType,

                      @FixedFieldAnnotations(length = 12, format = "\\d{12}")
                      String idDocumentNumber,

                      @FixedFieldAnnotations(length = 30)
                      String photographFile,

                      @FixedFieldAnnotations(length = 10, format = "\\d{10}")
                      Long mobileNumber,

                      @FixedFieldAnnotations(length = 35, format = ".+@.+\\..+")
                      String emailAddress) {
}





