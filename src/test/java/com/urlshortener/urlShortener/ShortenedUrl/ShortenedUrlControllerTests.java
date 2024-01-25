package com.urlshortener.urlShortener.ShortenedUrl;

import jakarta.servlet.ServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verifyNoInteractions;


@ExtendWith(MockitoExtension.class)
public class ShortenedUrlControllerTests {

    @Mock
    private ShortenedUrlService shortenedUrlService;
    private ShortenedUrlController controller;
    @Mock
    private ServletRequest context;

    @BeforeEach
    public void setUp() {
        controller = new ShortenedUrlController(shortenedUrlService, context);
    }
    @Test
    public void created_Shortened_Url_Should_Return_Bad_Request()
    {
        // Arrange
        String invalidUrl = "htp:/badurl.org";
        ShortenedUrlRequest shortenedUrlRequest = new ShortenedUrlRequest();
        shortenedUrlRequest.setUrl(invalidUrl);

        // Act
        ResponseEntity<String> response = controller.createShortenedUrl(shortenedUrlRequest);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("the url is not formatted correctly", response.getBody());
        verifyNoInteractions(shortenedUrlService);
    }

    @Test
    public void created_Shortened_Url_Should_Return_Ok_Result(){
        // Arrange
        String url = "https://example.com";
        ShortenedUrlRequest request = new ShortenedUrlRequest();
        request.setUrl(url);
        String code = "abc123";
        String shortUrl = "http://localhost:8080/api/abc123";

        Mockito.when(context.getScheme()).thenReturn("http");
        Mockito.when(context.getServerName()).thenReturn("localhost");
        Mockito.when(context.getServerPort()).thenReturn(8080);
        Mockito.when(shortenedUrlService.generateUniqueCode()).thenReturn(code);

        // Act
        ResponseEntity<String> response = controller.createShortenedUrl(request);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(shortUrl, response.getBody());
        Mockito.verify(shortenedUrlService).addShortenedUrl(Mockito.any(ShortenedUrl.class));
    }

    @Test
    public void redirect_with_short_url_should_return_not_found_result(){
        // Arrange
        String code = "invalidCode";
        Mockito.when(shortenedUrlService.getShortenedUrlByCode(code)).thenReturn(Optional.empty());

        // Act
        ResponseEntity response = controller.redirectWithShortUrl(code);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("", response.getBody());
    }

    @Test
    public void redirect_with_short_url_should_return_found_result() {
        // Arrange
        String code = "abc123";
        String longUrl = "https://example.com";

        // Mocking service behavior
        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setLongUrl(longUrl);
        Mockito.when(shortenedUrlService.getShortenedUrlByCode(code)).thenReturn(Optional.of(shortenedUrl));

        // Call method
        ResponseEntity response = controller.redirectWithShortUrl(code);

        // Assertions
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        HttpHeaders headers = response.getHeaders();
        assertEquals(longUrl, Objects.requireNonNull(headers.getLocation()).toString());
    }
}
