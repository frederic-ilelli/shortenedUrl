package com.urlshortener.urlShortener.ShortenedUrl;

import jakarta.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ShortenedUrlController {

    private final ShortenedUrlService shortenedUrlService;
    private final ServletRequest context;

    @Autowired
    public ShortenedUrlController(ShortenedUrlService shortenedUrlService, ServletRequest context)
    {
        this.shortenedUrlService = shortenedUrlService;
        this.context = context;
    }
    @PostMapping(path = "/create")
    public ResponseEntity<String> createShortenedUrl(@RequestBody ShortenedUrlRequest request) {

        // first try to create url
        URL url;
        try {
            url = URI.create(request.getUrl()).toURL();
        } catch (MalformedURLException ex)
        {
            return ResponseEntity.badRequest().body("the url is not formatted correctly");
        }

        // generate unique code
        String code = shortenedUrlService.generateUniqueCode();

        // populate object and call business logic layer
        ShortenedUrl shortenedUrl = new ShortenedUrl();
        shortenedUrl.setId(UUID.randomUUID());
        shortenedUrl.setLongUrl(url.toString());
        shortenedUrl.setCode(code);
        shortenedUrl.setShortUrl(String.format("%s://%s:%s/api/%s", context.getScheme(), context.getServerName(), context.getServerPort(), code));
        shortenedUrl.setCreatedOnUtc(LocalDateTime.now());

        shortenedUrlService.addShortenedUrl(shortenedUrl);

        return new ResponseEntity<>(shortenedUrl.getShortUrl(), HttpStatus.OK);
    }

    @GetMapping(path = "/api/{code}")
    public ResponseEntity redirectWithShortUrl(@PathVariable(value = "code") String code)
    {
        Optional<ShortenedUrl> shortenedUrl = shortenedUrlService.getShortenedUrlByCode(code);

        if(shortenedUrl.isEmpty())
        {
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", shortenedUrl.get().getLongUrl());
        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

}
