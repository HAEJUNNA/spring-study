package com.application.springstudy;

import com.application.springstudy.payment.Payment;
import com.application.springstudy.payment.PaymentService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        //객체 생성 및 관계 설정
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
        PaymentService paymentService = beanFactory.getBean(PaymentService.class);

        //객체 사용
        Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
        System.out.println(">>> payment1=" + payment1);

        Payment payment2 = paymentService.prepare(200L, "USD", BigDecimal.valueOf(80.12));
        System.out.println(">>> payment2=" + payment2);

        Thread.sleep(3000);

        Payment payment3 = paymentService.prepare(300L, "USD", BigDecimal.valueOf(12.57));
        System.out.println(">>> payment3=" + payment3);
    }
}
