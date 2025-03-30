package com.application.springstudy.exchangerate;

import com.application.springstudy.exchangerate.cache.ExchangeRateCacheProvider;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.application.springstudy
 * fileName       : CachedExchangeRateProvider
 * author         : NAHAEJUN
 * date           : 2025-03-23
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-23        NAHAEJUN              최초생성
 */
@RequiredArgsConstructor
public class CachedExchangeRateProvider implements ExchangeRateProvider{

    private static final Map<String, BigDecimal> CACHE_EXCHANGE_RATES = new HashMap<>(); //ConcurrentHashMap<>() 사용가능

    private final ExchangeRateProvider target;
    private final ExchangeRateCacheProvider cacheProvider;

    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
//        BigDecimal exchangeRate = CACHE_EXCHANGE_RATES.get(currency);
        BigDecimal exchangeRate = cacheProvider.getExchangeRate(currency);

        if ( isCachedDataEmpty(exchangeRate)) {
            return getNewExchangeRate(currency);
        }
        return exchangeRate;
    }

    private static boolean isCachedDataEmpty(BigDecimal exchangeRate) {
        return exchangeRate == null;
    }

    private BigDecimal getNewExchangeRate(String currency) throws URISyntaxException, IOException {
        BigDecimal exchangeRate = target.getExchangeRate(currency);
//        cacheProvider.setExchangeRate(currency, exchangeRate, LocalDateTime.now().plusSeconds(3));
        cacheProvider.setExchangeRate(currency, exchangeRate, Duration.ofSeconds(3)); // 레디스 보관 시간 지정
        return exchangeRate;
    }
}
