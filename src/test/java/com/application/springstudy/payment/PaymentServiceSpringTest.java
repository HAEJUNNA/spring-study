package com.application.springstudy.payment;

import com.application.springstudy.PaymentConfig;
import com.application.springstudy.TestPaymentConfig;
import com.application.springstudy.exchangerate.SturbExchangeRateProvider;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * packageName    : com.application.springstudy.payment
 * fileName       : PaymentServiceSpringTest
 * author         : NAHAEJUN
 * date           : 2025-04-19
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-04-19        NAHAEJUN              최초생성
 */
// 이테스트는 spring의 기능을 이용할거라는 표현, Autowired를 사용가능
@ExtendWith(SpringExtension.class)
// 메타 데이터, 해당 정보로스프링 컨테이너를 만들어서 해당 테스트를 돌려달라고 요청
// 기본적으로 스프링부트는 이런설정이 자동으로 되있지만, 스프링의 경우는 다 일일히 수동으로 해줘야한다.
@ContextConfiguration(classes = TestPaymentConfig.class)
//@SpringJUnitConfig(PaymentConfig.class)  @ExtendWith + @ContextConfiguration 을 합친 어노테이션
public class PaymentServiceSpringTest {

    @Autowired PaymentService paymentService;

    @Test
    void prepare() throws URISyntaxException, IOException {

        //given, when, then -> BDD(행동 주도 개발) 스타일

        //given - 수행 준비
        BigDecimal exchangeRate = BigDecimal.valueOf(1000);
        BigDecimal amount = BigDecimal.valueOf(50.7);

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
