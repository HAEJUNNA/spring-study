package com.application.springstudy;

import com.application.springstudy.exchangerate.*;
import com.application.springstudy.exchangerate.cache.ExchangeRateCacheProvider;
import com.application.springstudy.exchangerate.cache.InMemoryExchangeRateCacheProvider;
import com.application.springstudy.payment.ExchangeRateProvider;
import com.application.springstudy.payment.PaymentService;
import org.springframework.context.annotation.Bean;

/**
 * packageName    : com.application.springstudy
 * fileName       : PaymentConfig
 * author         : NAHAEJUN
 * date           : 2025-03-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-15        NAHAEJUN              최초생성
 */
public class PaymentConfig {
    // 객체를 넣어주는 메서드명은 그냥 get이아닌 소문자로 그대로 넣어주는게 관례
    @Bean
    public PaymentService paymentService(){
       return new PaymentService(cachedExchangeRateProvider());
    };

    @Bean
    public ExchangeRateProvider cachedExchangeRateProvider() {
        return new CachedExchangeRateProvider(exchangeRateProvider(), exchangeRateCacheProvider());
    }

    @Bean
    public ExchangeRateCacheProvider exchangeRateCacheProvider() {
        return new InMemoryExchangeRateCacheProvider();
//        return new RedisExchangeRateCacheProvider(redisTemplate()); //레디스 로 교체
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new HttpApiExchangeRateProvider();
    }
// 레디스 config
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate() {
//        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
//        connectionFactory.afterPropertiesSet();
//
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(connectionFactory);
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
//        redisTemplate.afterPropertiesSet();
//        return redisTemplate;
//    }
}
