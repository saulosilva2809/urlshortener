package com.saulo.urlshortener;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "original_url", nullable = false)
    private String originalUrl;


    @Column(name = "short_url", nullable = false)
    private String shortUrl;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;
}
