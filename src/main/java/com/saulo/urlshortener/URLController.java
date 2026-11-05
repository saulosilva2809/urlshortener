package com.saulo.urlshortener;

import com.saulo.urlshortener.DTOs.URLRequestDTO;
import com.saulo.urlshortener.DTOs.URLResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/url")
@RequiredArgsConstructor
public class URLController {

    private final URLService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<URLResponseDTO> shortenUrl(@RequestBody URLRequestDTO data) {
        String shortCode = urlService.shortenUrl(data.url());
        String fullShortUrl = String.format("https://sss.com/%s", shortCode);

        return ResponseEntity.status(HttpStatus.CREATED).body(new URLResponseDTO(fullShortUrl));
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        String originalUrl = urlService.getOriginalUrlOrThrow(shortUrl);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
}
