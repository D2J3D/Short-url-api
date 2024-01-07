package ru.gridusov.shorturl.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlDto implements Serializable {
    @NotBlank(message = "Url mustn't be equal to empty string (or null)")
    private String fullUrl;
    private String shortUrlKey;
    @Min(value = 0, message = "Amount of clicks should not be less than zero.")
    private Long clickAmount = 0L;
    private Timestamp expirationData;
}
