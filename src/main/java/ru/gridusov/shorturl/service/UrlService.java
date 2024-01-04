package ru.gridusov.shorturl.service;


import ru.gridusov.shorturl.entity.Url;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public interface UrlService {
    Url createShortUrl(Url url) throws NoSuchAlgorithmException;
    Optional<Url> findByKey(String key);
    Optional<Url> findByFullUrl(String fullUrl);
}
