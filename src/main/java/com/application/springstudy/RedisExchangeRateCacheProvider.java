package com.application.springstudy;

/**
 * packageName    : com.application.springstudy
 * fileName       : RedisExchangeRateCacheProvider
 * author         : NAHAEJUN
 * date           : 2025-03-29
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2025-03-29        NAHAEJUN              최초생성
 */
//@RequiredArgsConstructor
//class RedisExchangeRateCacheProvider implements ExchangeRateCacheProvider {
//
//    private final RedisTemplate redisTemplate;
//    private final RedisTemplate<String, Object> redisTemplate;
//
//    @Override
//    public BigDecimal getExchangeRate(String currency) throws URISyntaxException, IOException {
//        return redisTemplate.get(currency);
//        Object result = redisTemplate.opsForValue().get(currency);
//        if (result != null) {
//            return BigDecimal.valueOf((double) result);
//        }
//        return null;
//    }
//
//    @Override
//    public void setExchangeRate(String currency, BigDecimal exchangeRate, LocalDateTime expiryTime) {
//        redisTemplate.set(currency, exchangeRate, expiryTime);
//    public void setExchangeRate(String currency, BigDecimal exchangeRate, Duration timeout) {
//        redisTemplate.opsForValue().set(currency, exchangeRate, timeout);
//    }
//}