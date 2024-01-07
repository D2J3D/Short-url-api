package ru.gridusov.shorturl.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UrlDto implements Serializable {
    /**
     * Long url to be shortened, type: String
     */
    @NotBlank(message = "Url mustn't be equal to empty string (or null)")
    private String fullUrl;
    /**
     * Short key, by which GET request will be mapped to original, long url
     */
    private String shortUrlKey;
    /**
     * Date until which generated by service short link will be valid
     */
    private Timestamp expirationData;
}
