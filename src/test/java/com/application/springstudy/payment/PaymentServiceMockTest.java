package com.application.springstudy.payment;

import com.application.springstudy.TestPaymentConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

/**
 * packageName    : com.application.springstudy.payment
 * fileName       : PaymentServiceMockTest
 * author         : NAHAEJUN
 * date           : 2025-04-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-04-19        NAHAEJUN              최초생성
 */

@SpringJUnitConfig(TestPaymentConfig.class)
public class PaymentServiceMockTest {

    @Autowired
    PaymentService paymentService;
    @MockitoBean
    ExchangeRateProvider exchangeRateProvider;

    @Test
    void prepare() throws URISyntaxException, IOException {

        //given, when, then -> BDD(행동 주도 개발) 스타일

        //given - 수행 준비
        BigDecimal exchangeRate = BigDecimal.valueOf(1000);
        BigDecimal amount = BigDecimal.valueOf(50.7);

        //메서드 모킹 ,파라미터가 중요하지않기 떄문에 ArgumentMatchers.any 지정
        Mockito.when(exchangeRateProvider.getExchangeRate(any())).thenReturn(exchangeRate);

        //when - 실제 기능 수행 paymentService.prepare()를 검증 한다.
        // 이제 직접 일일히 만들필요없이 , 의존성을 주입받아 실행
        Payment payment = paymentService.prepare(100L, "USD", amount);

        //then - 수행 결과 검증
        //환율 확인
        assertThat(payment.getExchangeRate()).isEqualTo(exchangeRate);

        //통화량 확인
        assertThat(payment.getAmount()).isEqualTo(amount);

        //원화 환산금액 확인
        BigDecimal convertedAmount = payment.getConvertedAmount();
        assertThat(convertedAmount).isEqualByComparingTo(payment.getAmount().multiply(exchangeRate));

        //유효시간 확인
        LocalDateTime validUntil = payment.getValidUntil();
        assertThat(validUntil).isAfter(LocalDateTime.now())
                .isBefore(LocalDateTime.now().plusMinutes(30));

    }
}
