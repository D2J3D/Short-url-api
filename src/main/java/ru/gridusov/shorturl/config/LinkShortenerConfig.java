package ru.gridusov.shorturl.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "encryptor.url")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkShortenerConfig {
    private Integer shortUrlLength;
}
