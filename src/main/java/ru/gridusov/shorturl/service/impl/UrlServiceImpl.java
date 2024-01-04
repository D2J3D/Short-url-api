package ru.gridusov.shorturl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gridusov.shorturl.entity.Url;
import ru.gridusov.shorturl.repository.UrlRepository;
import ru.gridusov.shorturl.service.UniqueKeyGenerating;
import ru.gridusov.shorturl.service.UrlService;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

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
    public Url createShortUrl(Url url) throws NoSuchAlgorithmException {
        String shortKey = keyGenerator.generateHash(url.getKey()).substring(0, 7);
        for (int i = 0; i < (shortKey.length() / 7); i++){
            if (urlRepository.findByKey(shortKey).isEmpty()){
                break;
            }
            shortKey = shortKey.substring(i*7, 7 * (i + 1));
        }
//        if (urlRepository.findByKey(shortKey).isEmpty()){
//            throw new Exception("No opportunity to save new short urls");
//        }
        url.setKey(shortKey);
        return urlRepository.save(url);
    }

    @Override
    public Optional<Url> findByKey(String key) {
        return urlRepository.findByKey(key);
    }

    @Override
    public Optional<Url> findByFullUrl(String fullUrl) {
        return urlRepository.findByFullUrl(fullUrl);
    }
}
