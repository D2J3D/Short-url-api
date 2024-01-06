package ru.gridusov.shorturl.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
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

    @Column(nullable = false)
    private String fullUrl;

    @Column
    private Long clickAmount;

    @Column(nullable = false)
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Column(nullable = false)
    private Timestamp expirationDate;
}
