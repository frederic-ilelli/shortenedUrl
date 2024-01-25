package com.urlshortener.urlShortener.ShortenedUrl;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortenedUrlTests {
    @Test
    public void testIdGetterSetter() {
        // Arrange
        UUID id = UUID.randomUUID();
        ShortenedUrl shortenedUrl = new ShortenedUrl();

        // Act
        shortenedUrl.setId(id);

        // Assert
        assertEquals(id, shortenedUrl.getId());
    }

    @Test
    public void testLongUrlGetterSetter() {
        // Arrange
        String longUrl = "https://example.com";
        ShortenedUrl shortenedUrl = new ShortenedUrl();

        // Act
        shortenedUrl.setLongUrl(longUrl);

        // Assert
        assertEquals(longUrl, shortenedUrl.getLongUrl());
    }

    @Test
    public void testShortUrlGetterSetter() {
        // Arrange
        String shortUrl = "http://localhost:8080/abc123";
        ShortenedUrl shortenedUrl = new ShortenedUrl();

        // Act
        shortenedUrl.setShortUrl(shortUrl);

        // Assert
        assertEquals(shortUrl, shortenedUrl.getShortUrl());
    }

    @Test
    public void testCodeGetterSetter() {
        // Arrange
        String code = "abc123";
        ShortenedUrl shortenedUrl = new ShortenedUrl();

        // Act
        shortenedUrl.setCode(code);

        // Assert
        assertEquals(code, shortenedUrl.getCode());
    }

    @Test
    public void testCreatedOnUtcGetterSetter() {
        // Arrange
        LocalDateTime dateTime = LocalDateTime.now();
        ShortenedUrl shortenedUrl = new ShortenedUrl();

        // Act
        shortenedUrl.setCreatedOnUtc(dateTime);

        // Assert
        assertEquals(dateTime, shortenedUrl.getCreatedOnUtc());
    }
}
