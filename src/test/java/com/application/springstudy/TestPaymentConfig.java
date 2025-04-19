package com.application.springstudy;

import com.application.springstudy.exchangerate.SturbExchangeRateProvider;
import com.application.springstudy.payment.ExchangeRateProvider;
import com.application.springstudy.payment.PaymentService;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

/**
 * packageName    : com.application.springstudy
 * fileName       : TestPaymentConfig
 * author         : NAHAEJUN
 * date           : 2025-04-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-04-19        NAHAEJUN              최초생성
 */
public class TestPaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(null);
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new SturbExchangeRateProvider(BigDecimal.valueOf(1000));
    }
}
