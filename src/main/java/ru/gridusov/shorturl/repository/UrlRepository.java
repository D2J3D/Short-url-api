package ru.gridusov.shorturl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gridusov.shorturl.entity.Url;

import java.util.Optional;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Optional<Url> findByFullUrl(String fullUrl);
    Optional<Url> findByKey(String key);
}
