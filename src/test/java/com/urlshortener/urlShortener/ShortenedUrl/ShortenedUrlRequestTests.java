package com.urlshortener.urlShortener.ShortenedUrl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShortenedUrlRequestTests {
    @Test
    public void testUrlGetterSetter() {
        // Arrange
        String url = "https://example.com";
        ShortenedUrlRequest request = new ShortenedUrlRequest();

        // Act
        request.setUrl(url);

        // Assert
        assertEquals(url, request.getUrl());
    }
}
