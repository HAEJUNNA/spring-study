package com.application.springstudy;

import com.application.springstudy.exchangerate.SturbExchangeRateProvider;
import com.application.springstudy.payment.ExchangeRateProvider;
import com.application.springstudy.payment.PaymentService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
@Configuration (proxyBeanMethods = true)// 컴포넌트 스캔 대상. 프록시 기능으로 빈(Bean) 객체를 싱글톤으로 보장
public class TestPaymentConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService(exchangeRateProvider());
    }

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return new SturbExchangeRateProvider(BigDecimal.valueOf(1000));
    }
}
