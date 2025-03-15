package com.application.springstudy;

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
       return new PaymentService(getExchangeRateProvider());
    };
    
    public ExchangeRateProvider getExchangeRateProvider(){
        return new HttpApiExchangeRateProvider();
    }
}
