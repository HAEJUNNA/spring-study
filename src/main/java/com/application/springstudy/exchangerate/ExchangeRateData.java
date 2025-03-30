package com.application.springstudy.exchangerate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Map;

/**
 * packageName    : com.application.springstudy
 * fileName       : ExchangeRateData
 * author         : NAHAEJUN
 * date           : 2025-03-08
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-08        NAHAEJUN              최초생성
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record ExchangeRateData (
        String result,
        Map<String, BigDecimal> rates
) {}
