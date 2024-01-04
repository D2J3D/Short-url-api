package ru.gridusov.shorturl.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlDto {
    private String shortUrl;
    private Long clickAmount;
    private Timestamp expirationData;
}
