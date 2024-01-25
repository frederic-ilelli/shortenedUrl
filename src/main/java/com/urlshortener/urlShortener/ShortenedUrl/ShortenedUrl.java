package com.urlshortener.urlShortener.ShortenedUrl;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ShortenedUrls")
public class ShortenedUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "long_url", nullable = false)
    private String longUrl;

    @NotNull
    @Column(name = "short_url", nullable = false)
    private String shortUrl;

    @NotNull
    @Column(name = "code", nullable = false, unique = true, length = 7)
    private String code;

    @NotNull
    @Column(name = "created_on_utc", nullable = false)
    private LocalDateTime createdOnUtc;

    public ShortenedUrl()
    {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getCreatedOnUtc() {
        return createdOnUtc;
    }

    public void setCreatedOnUtc(LocalDateTime createdOnUtc) {
        this.createdOnUtc = createdOnUtc;
    }
}
