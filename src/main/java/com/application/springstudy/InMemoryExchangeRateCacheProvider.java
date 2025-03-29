package com.application.springstudy;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * packageName    : com.application.springstudy
 * fileName       : InMemoryExchangeRateCacheProvider
 * author         : NAHAEJUN
 * date           : 2025-03-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-29        NAHAEJUN              최초생성
 */
public class InMemoryExchangeRateCacheProvider implements ExchangeRateCacheProvider{
    private static final Map<String, BigDecimal> CACHED_EXCHANGE_RATES = new HashMap<>();
    private static final Map<String, LocalDateTime> CACHED_EXPIRY_TIMES = new HashMap<>();

    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        BigDecimal exchangeRate = getCachedExchangeRate(currency);
        if (isCachedDataEmpty(exchangeRate)) {
            return null;
        }
        if (isCachedDataExpired(currency)) {
            return null;
        }
        return exchangeRate;
    }

    private static BigDecimal getCachedExchangeRate(String currency) {
        return CACHED_EXCHANGE_RATES.get(currency);
    }

    private static boolean isCachedDataEmpty(BigDecimal exchangeRate) {
        return exchangeRate == null;
    }

    private static boolean isCachedDataExpired(String currency) {
        LocalDateTime expiryTime = CACHED_EXPIRY_TIMES.get(currency);
        return expiryTime == null || LocalDateTime.now().isAfter(expiryTime);
    }

    @Override
    public void setExchangeRate(String currency, BigDecimal exchangeRate, Duration timeout) {
        CACHED_EXCHANGE_RATES.put(currency, exchangeRate);
        CACHED_EXPIRY_TIMES.put(currency, LocalDateTime.now().plus(timeout));
    }
}
