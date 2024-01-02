package ru.gridusov.shorturl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gridusov.shorturl.entity.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByFullUrl(String fullUrl);
    Url findByKey(String key);
}
