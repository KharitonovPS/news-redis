package org.kharitonov.newsredis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("News")
public class News implements Serializable {

    private Long id;

    private String title;

    private String publisher;
}
