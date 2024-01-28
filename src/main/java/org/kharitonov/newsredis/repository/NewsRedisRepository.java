package org.kharitonov.newsredis.repository;

import lombok.extern.slf4j.Slf4j;
import org.kharitonov.newsredis.entity.News;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class NewsRedisRepository {

    private final RedisTemplate<String, News> template;

    public NewsRedisRepository(
//            @Qualifier("redisTemplate")
            RedisTemplate<String, News> template
    ) {
        this.template = template;
    }

    public News save(News news) {
        String key = "News:" + news.getTitle();
        var valueOperations = template.opsForValue();
        valueOperations.set(key, news);
        log.info("News Added -> {}", news);
        return news;
    }

    public List<News> findAll() {
        Set<String> keys = template.keys("News:*");
        List<News> newsList = new ArrayList<>();

        var valueOperations = template.opsForValue();
        if (keys != null) {
            for (String key : keys) {
                News news = valueOperations.get(key);
                if (news != null) {
                    newsList.add(news);
                }
            }
        }
        return newsList;
    }

    public News findNewsByTitle(String title) {
        var valueOperations = template.opsForValue();
        Optional<News> news = Optional.ofNullable(
                valueOperations.get("News:" + title)
        );
        return news.orElseThrow(
                () -> new NoSuchElementException(
                        "Can not find News with title -> " + title
                ));
    }

    public String deleteNews(String title) {
        template.delete("News:" + title);
        return "News removed";
    }
}
