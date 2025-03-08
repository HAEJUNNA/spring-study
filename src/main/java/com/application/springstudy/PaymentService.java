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
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-08        NAHAEJUN              최초생성
 */
public class PaymentService {
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
    private static BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        //환율 조회
        URI uri = new URI("https://open.er-api.com/v6/latest/" + currency);
        HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String body = bufferedReader.lines().collect(Collectors.joining());
        //System.out.println(">>> body=" + body);

        ObjectMapper objectMapper = new ObjectMapper();
        ExchangeRateData data = objectMapper.readValue(body, ExchangeRateData.class);
        //문자열(바이트배열) -> 객체로 변환 --> 디시리얼라이즈 (디시리얼라이저)
        //객체 -> 문자열(바이트배열)로 변환 --> 시리얼라이즈 (시리얼라이저)
        //System.out.println(">>> data=" + data);
        BigDecimal exchangeRate = data.rates().get("KRW");
        //System.out.println(">>> exchangeRate=" + exchangeRate);
        return exchangeRate;
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
        //테스트 코드
        PaymentService paymentService = new PaymentService();
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(">>> payment=" + payment);
    }
}
