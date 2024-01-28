package org.kharitonov.newsredis.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("News")
@EqualsAndHashCode
public class News implements Serializable {

    private Long id;

    private String title;

    private String publisher;
}
