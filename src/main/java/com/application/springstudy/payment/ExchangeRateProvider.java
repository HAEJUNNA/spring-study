package com.application.springstudy.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

/**
 * packageName    : com.application.springstudy
 * fileName       : ExchangeRateProvider
 * author         : NAHAEJUN
 * date           : 2025-03-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-08        NAHAEJUN              최초생성
 */
public interface ExchangeRateProvider {
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException;
}
