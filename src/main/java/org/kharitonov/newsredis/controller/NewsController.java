package org.kharitonov.newsredis.controller;

import org.kharitonov.newsredis.entity.News;
import org.kharitonov.newsredis.repository.NewsRedisRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class NewsController {

    private final NewsRedisRepository dao;

    public NewsController(NewsRedisRepository newsRedisRepository) {
        this.dao = newsRedisRepository;
    }

    @PostMapping("/publish")
    public News save(@RequestBody News news) {
        return dao.save(news);
    }

    @GetMapping
    public List<News> getAllNews() {
        return dao.findAll();
    }

    @GetMapping("/{title}")
    public News findNewsByTitle(@PathVariable String title) {
        return dao.findNewsByTitle(title);
    }

    @DeleteMapping("/{title}")
    public String remove(@PathVariable String title) {
        return dao.deleteNews(title);
    }
}