package ru.gridusov.shorturl.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String key;
    @Column(nullable = false)
    private String fullUrl;
    @Column
    private Long clickAmount;
    @Column(nullable = false)
    private Timestamp createdAt;
    @Column
    private Timestamp expirationDate;
}
