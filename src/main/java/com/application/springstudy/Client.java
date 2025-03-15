package com.application.springstudy;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

/**
 * packageName    : com.application.springstudy
 * fileName       : Client
 * author         : NAHAEJUN
 * date           : 2025-03-15
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-15        NAHAEJUN              최초생성
 */
public class Client {
    public static void main(String[] args) throws URISyntaxException, IOException {
        // 관계 설정의 책임을 내가 갖는게 아닌 분리 한다.(관계 설정 책임 분리)
        PaymentService paymentService = new PaymentService(new HttpApiExchangeRateProvider());
        Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(">>> payment=" + payment);
    }
}
