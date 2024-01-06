package ru.gridusov.shorturl.model.dto;

import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlDto {
    private String fullUrl;
    private String shortUrlKey;
    private Integer clickAmount = 0;
    private Timestamp expirationData;
}
