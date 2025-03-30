package com.application.springstudy.exchangerate.cache;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.Duration;

/**
 * packageName    : com.application.springstudy
 * fileName       : ExchangeRateCacheProvider
 * author         : NAHAEJUN
 * date           : 2025-03-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-29        NAHAEJUN              최초생성
 */
public interface ExchangeRateCacheProvider {
    BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException;
    void setExchangeRate(String currency, BigDecimal exchangeRate, Duration timeout);
}
