package com.sparta.finalproject.global.config.refreshToken;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash(value = "refreshToken", timeToLive =14 * 24 * 60 * 60 * 1000L)
public class RefreshToken {

    @Id
    private String refreshToken;
    @Indexed
    private Long kakaoId;

    public RefreshToken(String refreshToken, Long kakaoId) {
        this.refreshToken = refreshToken;
        this.kakaoId = kakaoId;
    }
}