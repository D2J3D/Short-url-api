package ru.gridusov.shorturl.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gridusov.shorturl.config.LinkShortenerConfig;
import ru.gridusov.shorturl.model.entity.Url;
import ru.gridusov.shorturl.exceptions.NotEnoughSpaceError;
import ru.gridusov.shorturl.repository.UrlRepository;
import ru.gridusov.shorturl.service.UniqueKeyGenerating;
import ru.gridusov.shorturl.service.UrlService;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;
    private final UniqueKeyGenerating keyGenerator;
    private final LinkShortenerConfig linkShortenerConfig;

    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository, UniqueKeyGenerating keyGenerator, LinkShortenerConfig linkShortenerConfig) {
        this.urlRepository = urlRepository;
        this.keyGenerator = keyGenerator;
        this.linkShortenerConfig = linkShortenerConfig;
    }

    @Override
    public Url createShortUrl(Url url) throws NoSuchAlgorithmException {
        if (urlRepository.existsByFullUrl(url.getFullUrl())) {
            Url foundUrl = urlRepository.findByFullUrl(url.getFullUrl()).get();
            foundUrl.setClickAmount(foundUrl.getClickAmount() + 1);
            return urlRepository.save(foundUrl);
        }
        log.info("Forming a short key for url: " + url.getFullUrl());
        url.setShortUrlKey(formShortUrl(url.getFullUrl()));
        log.info("Saving the url into the database: " + url.getFullUrl() + ". Short url: " + url.getShortUrlKey());
        return urlRepository.save(url);
    }

    @Override
    public Optional<Url> findByShortUrlKey(String key) {
        Optional<Url> urlCandidate = urlRepository.findByShortUrlKey(key);
        if (urlCandidate.isPresent()){
            Url url = urlCandidate.get();
            url.setClickAmount(url.getClickAmount() + 1);
            return Optional.of(urlRepository.save(url));
        }
        log.info("Getting url with key = " + key);
        return urlCandidate;
    }

    @Override
    public void deleteUserByKey(String key) {
        log.info("Deleting the url with key: " + key + ".");
        urlRepository.deleteByShortUrlKey(key);
    }

    @Override
    public boolean ifExists(Long id) {
        return urlRepository.existsById(id);
    }

    private String formShortUrl(String fullUrl) throws NoSuchAlgorithmException {
        String key = extractUniqueKeyFromHash(keyGenerator.generateHash(fullUrl));
        if (urlRepository.findByShortUrlKey(key).isPresent()) {
            throw new NotEnoughSpaceError("No opportunity to save new short urls");
        }
        return key;
    }

    private String extractUniqueKeyFromHash(String hash) {
        String key = hash.substring(0, linkShortenerConfig.getShortUrlLength());
        for (int i = 1; i < (hash.length() / linkShortenerConfig.getShortUrlLength()); i++) {
            if (urlRepository.findByShortUrlKey(key).isEmpty()) {
                break;
            }
            key = hash.substring(i * linkShortenerConfig.getShortUrlLength(), linkShortenerConfig.getShortUrlLength() * (i + 1));
        }
        return key;
    }
}
