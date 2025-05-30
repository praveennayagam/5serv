package pojo;

import annotation.FixedFieldAnnotations;

public record IpAddress(
                        @FixedFieldAnnotations(length = 15)
                        String ipAddress,
                        @FixedFieldAnnotations(length = 25)
                        String hostName,
                        @FixedFieldAnnotations(length = 6)
                        String recordCount,
                        @FixedFieldAnnotations(length = 9, format = "\\d{7}.\\d{1}")
                        String processingTime) {
}
