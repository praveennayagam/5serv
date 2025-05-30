package pojo;
import annotation.FixedFieldAnnotations;

public record Address(
                      @FixedFieldAnnotations(length = 3, format = "\\d{1,3}")
                      Integer doorNo,

                      @FixedFieldAnnotations(length = 15, pattern = "^[A-Za-z\\s]+$")
                      String street,

                      @FixedFieldAnnotations(length = 15, pattern = "^[A-Za-z\\s]+$")
                      String city,

                      @FixedFieldAnnotations(length = 5, pattern = "^[A-Za-z]+$")
                      String state,

                      @FixedFieldAnnotations(length = 5, format = "\\d{5}")
                      Integer zipCode) {
}