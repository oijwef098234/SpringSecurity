package com.example.springsecurity.global.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisKeyValueAdapter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories(
        enableKeyspaceEvents = RedisKeyValueAdapter.EnableKeyspaceEvents.ON_STARTUP,
        keyspaceNotificationsConfigParameter = ""
)
@RequiredArgsConstructor
public class RedisConfig {
    @Value("${redis.host}")
    private String host;

    @Value("${redis.port}")
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() { // redis와 서버간 통신을 위한 객체 생성
        return new LettuceConnectionFactory(host, port);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>(); // redis를 설정할때 가장 많이 쓰이는 객체 생성
        redisTemplate.setConnectionFactory(connectionFactory); // 서버 연결 설정

        // Redis key와 value에 대한 Serializer 설정
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // redis에 저장되는건 무조건 String
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // Json으로 저장
        redisTemplate.setHashKeySerializer(new StringRedisSerializer()); // hash key도 문자열로 저장
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer()); // hash key도 json으로 저장

        return redisTemplate;
    }
}
