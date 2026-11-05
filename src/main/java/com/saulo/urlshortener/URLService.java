package com.saulo.urlshortener;

import com.saulo.urlshortener.exceptions.URLExpiredException;
import com.saulo.urlshortener.exceptions.URLNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class URLService {

    private final URLRepository urlRepository;
    private final ShortCodeGenerator shortCodeGenerator;

    public String shortenUrl(String originalUrl) {
        String shortCode = shortCodeGenerator.generate();

        URL url = new URL();
        url.setOriginalUrl(originalUrl);
        url.setShortUrl(shortCode);
        url.setExpirationDate(LocalDateTime.now().plusDays(30));

        urlRepository.save(url);

        return shortCode;
    }

    public String getOriginalUrlOrThrow(String shortUrl) {
        URL url = urlRepository.findByShortUrl(shortUrl)
                .orElseThrow(() -> new URLNotFoundException());

        if (url.getExpirationDate().isBefore(LocalDateTime.now())) {
            urlRepository.delete(url);
            throw new URLExpiredException();
        }

        return url.getOriginalUrl();
    }
}