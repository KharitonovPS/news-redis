package org.kharitonov.newsredis.controller;

import org.kharitonov.newsredis.entity.News;
import org.kharitonov.newsredis.repository.NewsDao;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class RedisController {

    private final NewsDao dao;

    public RedisController(NewsDao newsDao) {
        this.dao = newsDao;
    }

    @PostMapping("/publish")
    public News save(@RequestBody News news) {
        return dao.save(news);
    }

    @GetMapping
    public List<News> getAllNews() {
        return dao.findAll();
    }

    @GetMapping("/{id}")
    public News findNewsById(@PathVariable long id) {
        return dao.findNewsById(id);
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable long id) {
        return dao.deleteNews(id);
    }
}