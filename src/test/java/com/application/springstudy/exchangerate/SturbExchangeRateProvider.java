package com.application.springstudy.exchangerate;

import com.application.springstudy.payment.ExchangeRateProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

/**
 * packageName    : com.application.springstudy.exchangerate
 * fileName       : SturbExchangeRateProvider
 * author         : NAHAEJUN
 * date           : 2025-04-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-04-19        NAHAEJUN              최초생성
 */
public class SturbExchangeRateProvider implements ExchangeRateProvider {

    private final BigDecimal exchangeRate;

    public SturbExchangeRateProvider(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }


    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        return BigDecimal.valueOf(1000);

    }

}
