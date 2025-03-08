package com.application.springstudy;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

/**
 * packageName    : com.application.springstudy
 * fileName       : HttpApiExchangeRateProvider
 * author         : NAHAEJUN
 * date           : 2025-03-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-08        NAHAEJUN              최초생성
 */
public class HttpApiExchangeRateProvider implements ExchangeRateProvider {

    // 메서드 추출
    // 관심사 분리 -> 가장 좋은 방법은 메서드 추출
    @Override
    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
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
}
