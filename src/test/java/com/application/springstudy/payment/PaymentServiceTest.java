package com.application.springstudy.payment;

import com.application.springstudy.exchangerate.HttpApiExchangeRateProvider;
import com.application.springstudy.exchangerate.SturbExchangeRateProvider;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

//import yamsroun.learn_spring.exchangerate.HttpApiExchangeRateProvider;

import static org.assertj.core.api.Assertions.assertThat;

//테스트 코드 수행
// - 수동: 각 개발자 PC
// - 자동: Git Push, Pull Request, Merge, CI(Build), ...
class PaymentServiceTest {

    @Test
    void prepare() throws URISyntaxException, IOException {



        //given, when, then -> BDD(행동 주도 개발) 스타일

        //given - 수행 준비
//        ExchangeRateProvider exchangeRateProvider = new HttpApiExchangeRateProvider();
        BigDecimal exchangeRate = BigDecimal.valueOf(1000);
        BigDecimal amount = BigDecimal.valueOf(50.7);
        ExchangeRateProvider exchangeRateProvider = new SturbExchangeRateProvider(exchangeRate);
        PaymentService paymentService = new PaymentService(exchangeRateProvider);

        //when - 실제 기능 수행 paymentService.prepare()를 검증 한다.
        Payment payment = paymentService.prepare(100L, "USD", amount);

        //then - 수행 결과 검증
        //환율 확인
//        BigDecimal exchangeRate = payment.getExchangeRate();
//        assertThat(exchangeRate).isGreaterThan(BigDecimal.ZERO);
        assertThat(payment.getExchangeRate()).isEqualTo(exchangeRate);

        //통화량 확인
        assertThat(payment.getAmount()).isEqualTo(amount);

        //원화 환산금액 확인
        BigDecimal convertedAmount = payment.getConvertedAmount();
        // BigDecimal은 isEqualTo()로 비교하면 안된다.
//        assertThat(convertedAmount).isEqualTo(payment.getAmount().multiply(exchangeRate));
        assertThat(convertedAmount).isEqualByComparingTo(payment.getAmount().multiply(exchangeRate));

        //유효시간 확인
        LocalDateTime validUntil = payment.getValidUntil();
        //assertThat(validUntil).isEqualTo(LocalDateTime.now().plusMinutes(30)); //마이크로 초 차이로 실패
        assertThat(validUntil).isAfter(LocalDateTime.now())
                .isBefore(LocalDateTime.now().plusMinutes(30));

        //TC(Test Case) -> 테스트 코드에서는 테스트 메서드 하나하나
        //테스트 코드 작성할 때
        // - 의미 있는 테스트 코드여야 한다. (비즈니스 로직이 잘 수행되는지 잘 점검하고, 사람의 실수를 잘 검증해줘야 한다.)
        // - 테스트 코드가 쉽게 깨지지 않는 코드를 작성해야 한다. (일단 크기가 작아야 한다.)
        // - 하지만, 테스트 검증은 최대한 타이트해야 한다.
    }
}