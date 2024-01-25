package com.urlshortener.urlShortener.ShortenedUrl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, UUID> {
    boolean existsByCode(String code);

    Optional<ShortenedUrl> findByCode(@NonNull String code);

}
