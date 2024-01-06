package ru.gridusov.shorturl.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.gridusov.shorturl.model.entity.Url;
import ru.gridusov.shorturl.repository.UrlRepository;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Component
public class UrlWatcher {
    private final UrlRepository urlRepository;

    @Autowired
    public UrlWatcher(final UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    @Scheduled(cron = "0 0 * * * *")
    public void deleteExpiredUrls(){
        List<Url> allUrls = urlRepository.findAll();
        for (Url url : allUrls){
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            if (currentTime.after(url.getExpirationDate())){
                log.info("Deleting a url with shortUrlKey = " + url.getShortUrlKey() + ".");
                urlRepository.deleteById(url.getId());
            }
            else{
                log.info(url.getFullUrl() + " is not expired");
            }
        }
    }
}
