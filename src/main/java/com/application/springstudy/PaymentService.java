package com.application.springstudy;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

/**
 * packageName    : com.application.springstudy
 * fileName       : PaymentService
 * author         : NAHAEJUN
 * date           : 2025-03-08
 * description    :
 * 디자인 - > 템플릿 메서드 패턴
 * 일부기능만을 교체 할때 사용, 다른건 기본 베이스 이지만,. 기능하나가 확장이 필요하는 경우 사용
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-08        NAHAEJUN              최초생성
 */
public class PaymentService {

    private final ExchangeRateProvider exchangeRateProvider;

    public PaymentService(ExchangeRateProvider provider) {
        this.exchangeRateProvider = provider;
    }

    public Payment prepare(Long orderId, String currency, BigDecimal amount) throws URISyntaxException, IOException {

        BigDecimal exchangeRate = exchangeRateProvider.getExchangeRate(currency);

        //금액 계산
        BigDecimal convertedAmount = exchangeRate.multiply(amount);

        //유효시간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, amount,
                exchangeRate, convertedAmount, validUntil);
    }


    public static void main(String[] args) throws URISyntaxException, IOException {
        //테스트 코드
        ExchangeRateProvider provider = new HttpApiExchangeRateProvider();
        PaymentService paymentService = new PaymentService(provider);
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(">>> payment=" + payment);
    }
}
