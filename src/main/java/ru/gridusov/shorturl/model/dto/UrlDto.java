package ru.gridusov.shorturl.model.dto;

import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlDto implements Serializable {
    private String fullUrl;
    private String shortUrlKey;
    private Long clickAmount = 0L;
    private Timestamp expirationData;
}
