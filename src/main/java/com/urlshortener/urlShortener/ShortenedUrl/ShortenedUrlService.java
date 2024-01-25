package com.urlshortener.urlShortener.ShortenedUrl;

import java.util.Optional;

public interface ShortenedUrlService {
    String generateUniqueCode();

    void addShortenedUrl(ShortenedUrl shortenedUrl);

    Optional<ShortenedUrl> getShortenedUrlByCode(String code);
}
