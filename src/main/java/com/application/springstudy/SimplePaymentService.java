package com.application.springstudy;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;

/**
 * packageName    : com.application.springstudy
 * fileName       : SimplePaymentService
 * author         : NAHAEJUN
 * date           : 2025-03-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-08        NAHAEJUN              최초생성
 */
public class SimplePaymentService extends PaymentService{

    @Override
    protected BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
        return BigDecimal.valueOf(1000);
    }
}
