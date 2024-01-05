package ru.gridusov.shorturl.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.gridusov.shorturl.model.entity.Url;
import ru.gridusov.shorturl.exceptions.NotEnoughSpaceError;
import ru.gridusov.shorturl.repository.UrlRepository;
import ru.gridusov.shorturl.service.UniqueKeyGenerating;
import ru.gridusov.shorturl.service.UrlService;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Slf4j
@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final UniqueKeyGenerating keyGenerator;
    @Autowired
    public UrlServiceImpl(UrlRepository urlRepository, UniqueKeyGenerating keyGenerator){
        this.urlRepository = urlRepository;
        this.keyGenerator = keyGenerator;
    }

    @Override
    @CachePut(value = "urls", key = "#url.shortUrlKey")
    public Url createShortUrl(Url url) throws NoSuchAlgorithmException {
        log.info("Forming a short key for url: " + url.getFullUrl());
        url.setShortUrlKey(formShortUrl(url.getFullUrl()));
        log.info("Saving the url into the database: " + url.getFullUrl() + ". Short url: " + url.getShortUrlKey());
        return urlRepository.save(url);
    }

    @Override
    @Cacheable(value = "urls", key = "#key")
    public Optional<Url> findByShortUrlKey(String key) {
        log.info("Getting url with key = " + key);
        return urlRepository.findByShortUrlKey(key);
    }

    @Override
    public Url findByFullUrl(String fullUrl) {
        log.info("Getting url with full url = " + fullUrl);
        return urlRepository.findByFullUrl(fullUrl).orElseThrow(() -> new EntityNotFoundException("Not found entity with full url: " + fullUrl) );
    }

    @Override
    @CacheEvict(value = "urls", key = "#id")
    public void deleteUser(Long id) {
        log.info("Deleting the url with id: " + id + ".");
        urlRepository.deleteById(id);
    }

    @Override
    @CacheEvict(value = "urls", key = "#key")
    public void deleteUserByKey(String key) {
        log.info("Deleting the url with key: " + key + ".");
        urlRepository.deleteByShortUrlKey(key);
    }

    private String formShortUrl(String fullUrl) throws NoSuchAlgorithmException {
        String key = extractUniqueKeyFromHash(keyGenerator.generateHash(fullUrl));
        if (urlRepository.findByShortUrlKey(key).isPresent()){
            throw new NotEnoughSpaceError("No opportunity to save new short urls");
        }
        return key;
    }

    private String extractUniqueKeyFromHash(String hash){
        String key = hash.substring(0, 7);
        for (int i = 1; i < (hash.length() / 7); i++){
            if (urlRepository.findByShortUrlKey(key).isEmpty()){
                break;
            }
            key = hash.substring(i*7, 7 * (i + 1));
        }
        return key;
    }
}
