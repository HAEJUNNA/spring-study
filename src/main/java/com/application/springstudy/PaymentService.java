package com.application.springstudy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

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
public abstract class PaymentService {
    public Payment prepare(Long orderId, String currency, BigDecimal amount) throws URISyntaxException, IOException {

        BigDecimal exchangeRate = getExchangeRate(currency);

        //금액 계산
        BigDecimal convertedAmount = exchangeRate.multiply(amount);

        //유효시간 계산
        LocalDateTime validUntil = LocalDateTime.now().plusMinutes(30);

        return new Payment(orderId, currency, amount,
                exchangeRate, convertedAmount, validUntil);
    }

    // 메서드 추출
    // 관심사 분리 -> 가장 좋은 방법은 메서드 추출
    abstract protected BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException;

    public static void main(String[] args) throws URISyntaxException, IOException {
        //테스트 코드
        PaymentService paymentService = new HttpApiPaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(">>> payment=" + payment);
    }
}
