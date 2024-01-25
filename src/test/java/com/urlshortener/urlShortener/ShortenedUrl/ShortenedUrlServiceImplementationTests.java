package com.urlshortener.urlShortener.ShortenedUrl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ShortenedUrlServiceImplementationTests {
    private ShortenedUrlServiceImplementation service;

    @Mock
    private ShortenedUrlRepository shortenedUrlRepository;

    @BeforeEach
    public void setUp() {
        service = new ShortenedUrlServiceImplementation(shortenedUrlRepository);
    }

    @Test
    public void testGenerateUniqueCode() {
        // Arrange
        Mockito.when(shortenedUrlRepository.existsByCode(anyString())).thenReturn(false);

        // Act
        String code = service.generateUniqueCode();

        // Assert
        assertNotNull(code);
        assertEquals(7, code.length());
    }
    @Test
    public void testGenerateUniqueCode_ExistingCode() {
        // Mocking repository behavior
        Mockito.when(shortenedUrlRepository.existsByCode(anyString())).thenReturn(true, false);

        // Call method
        String code = service.generateUniqueCode();

        // Assertions
        assertNotNull(code);
        assertEquals(7, code.length());
        Mockito.verify(shortenedUrlRepository, times(2)).existsByCode(anyString());
    }

    @Test
    public void testGetShortenedUrlByCode() {
        // Arrange
        String code = "abc123";
        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setId(UUID.randomUUID());
        shortenedUrl.setCode(code);
        shortenedUrl.setLongUrl("https://example.com");
        Mockito.when(shortenedUrlRepository.findByCode(code)).thenReturn(Optional.of(shortenedUrl));

        // Act
        Optional<ShortenedUrl> result = service.getShortenedUrlByCode(code);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(shortenedUrl, result.get());
    }

    @Test
    public void testGetShortenedUrlByCode_NotFound() {
        // Arrange
        String code = "invalidCode";
        Mockito.when(shortenedUrlRepository.findByCode(code)).thenReturn(Optional.empty());

        // Act
        Optional<ShortenedUrl> result = service.getShortenedUrlByCode(code);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    public void testAddShortenedUrl() {
        // Arrange
        ShortenedUrl shortenedUrl = new ShortenedUrl();

        // Act
        service.addShortenedUrl(shortenedUrl);

        // Assert
        Mockito.verify(shortenedUrlRepository, times(1)).save(shortenedUrl);
    }

}
