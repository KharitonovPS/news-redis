package org.kharitonov.newsredis.repository;

import lombok.extern.slf4j.Slf4j;
import org.kharitonov.newsredis.entity.News;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Repository
public class NewsRedisRepository {

    private final RedisTemplate template;

    public NewsRedisRepository(@Qualifier("redisTemplate") RedisTemplate template) {
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
        for (String key : keys) {
            News news = (News) valueOperations.get(key);
            if (news != null) {
                newsList.add(news);
            }
        }
        return newsList;
    }

    public News findNewsByTitle(String title) {
        return (News) template.opsForValue().get("News:" + title);
    }

    public String deleteNews(String title) {
        template.delete("News:" + title);
        return "News removed";
    }
}
