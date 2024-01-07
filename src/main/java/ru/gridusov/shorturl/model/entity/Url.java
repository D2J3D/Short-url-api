package ru.gridusov.shorturl.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

@Entity
@Table(name = "url")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Url implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_id_seq" )
    @SequenceGenerator(name = "url_id_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String shortUrlKey;

    @Column
    @NotBlank(message = "Url mustn't be equal to empty string (or null)")
    private String fullUrl;

    @Column
    @Min(value = 0, message = "Amount of clicks should not be less than zero.")
    private Long clickAmount;

    @Column
    @NotNull(message = "Date of creation can't be null.")
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Column(nullable = false)
    @NotNull(message = "Expiration date can't be null.")
    private Timestamp expirationDate;
}
