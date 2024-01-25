package com.urlshortener.urlShortener.ShortenedUrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class ShortenedUrlServiceImplementation implements ShortenedUrlService {
    private final Random _random = new Random();

    private final ShortenedUrlRepository shortenedUrlRepository;

    @Autowired
    public ShortenedUrlServiceImplementation(ShortenedUrlRepository shortenedUrlRepository)
    {
        this.shortenedUrlRepository = shortenedUrlRepository;
    }

    public Optional<ShortenedUrl> getShortenedUrlByCode(String code)
    {
        return shortenedUrlRepository.findByCode(code);

    }

    public String generateUniqueCode() {
        int numberOfCharsInShortLink = 7;
        char[] codeChars = new char[numberOfCharsInShortLink];

        while (true)
        {
            for (int i = 0; i < numberOfCharsInShortLink; i++) {
                String _alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
                int randomIndex = _random.nextInt(_alphabet.length() - 1);

                codeChars[i] = _alphabet.charAt(randomIndex);
            }

            String code = new String(codeChars);

            if(!shortenedUrlRepository.existsByCode(code))
            {
                return code;
            }
        }
    }

    public void addShortenedUrl(ShortenedUrl shortenedUrl) {
        shortenedUrlRepository.save(shortenedUrl);
    }
}
