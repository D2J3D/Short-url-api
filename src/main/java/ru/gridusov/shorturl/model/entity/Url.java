package ru.gridusov.shorturl.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "url")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "url_id_seq" )
    @SequenceGenerator(name = "url_id_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String shortUrlKey;

    @Column(nullable = false, unique = true)
    private String fullUrl;

    @Column(columnDefinition = "integer default 0")
    private Integer clickAmount = 0;

    @Column
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    @Column
    private Timestamp expirationDate;
}
