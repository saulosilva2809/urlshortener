package com.saulo.urlshortener;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface URLRepository extends JpaRepository<URL, UUID> {

    Optional<URL> findByShortUrl(String url);
}
