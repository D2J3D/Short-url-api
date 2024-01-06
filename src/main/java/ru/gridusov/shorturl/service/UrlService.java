package ru.gridusov.shorturl.service;


import ru.gridusov.shorturl.model.entity.Url;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface UrlService {
    Url createShortUrl(Url url) throws NoSuchAlgorithmException;
    Optional<Url> findByShortUrlKey(String key);
    boolean ifExists(Long id);
    void deleteUserByKey(String key);
}
