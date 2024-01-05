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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String shortUrlKey;
    @Column(nullable = false)
    private String fullUrl;
    @Column(columnDefinition = "bigint default 0")
    private Long clickAmount;
    @Column(nullable = false)
    private Timestamp createdAt;
    @Column
    private Timestamp expirationDate;
}
