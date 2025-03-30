package com.application.springstudy.exchangerate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

/**
 * packageName    : com.application.springstudy
 * fileName       : SimpleExchangeRateProvider
 * author         : NAHAEJUN
 * date           : 2025-03-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-08        NAHAEJUN              최초생성
 */
public class SimpleExchangeRateProvider implements ExchangeRateProvider {

    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        return BigDecimal.valueOf(1000);
    }
}
