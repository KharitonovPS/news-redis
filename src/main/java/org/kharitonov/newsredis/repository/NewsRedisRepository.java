package org.kharitonov.newsredis.repository;

import org.kharitonov.newsredis.entity.News;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class NewsRedisRepository {

    private static final String HASH_KEY = "News";
    private final RedisTemplate template;

    public NewsRedisRepository(@Qualifier("redisTemplate") RedisTemplate template) {
        this.template = template;
    }


    public News save(News news) {
        template.opsForHash().put(HASH_KEY, news.getId(), news);
        return news;
    }

    public List<News> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public News findNewsById(long id) {
        return (News) template.opsForHash().get(HASH_KEY, id);
    }

    public String deleteNews(long id) {
        template.opsForHash().delete(HASH_KEY, id);
        return "News removed";
    }
}
