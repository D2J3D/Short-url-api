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
    private Long clickAmount;
    private Timestamp createdAt;
    private Timestamp expirationData;
}
