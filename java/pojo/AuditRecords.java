package pojo;

import annotation.FixedFieldAnnotations;

import java.time.LocalDateTime;

public record AuditRecords(
                           @FixedFieldAnnotations(length = 8, format = "\\d{8}")
                           Long loanId,
                           @FixedFieldAnnotations(length = 15)
                           String userId,
                           @FixedFieldAnnotations(length = 20, allowedValues = "Login,Logout,Update,Delete,Read,Write,")
                           String actionType,
                           @FixedFieldAnnotations(length = 19, pattern = "dd-MM-yyyy HH:mm:ss")
                           LocalDateTime timeStamp,
                           @FixedFieldAnnotations(length = 20)
                           String moduleName,
                           @FixedFieldAnnotations(length = 8)
                           String responseTime,
                           @FixedFieldAnnotations(length = 3, format = "\\d{3}")
                           Integer statusCode,
                           @FixedFieldAnnotations(length = 55)
                           IpAddress ipAddress) {
}
