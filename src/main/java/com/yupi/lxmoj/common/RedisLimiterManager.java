//package com.yupi.lxmoj.common;
//
///**
// * @ClassName RedisLimiterManager
// */
//import com.yupi.lxmoj.exception.BusinessException;
//import org.redisson.api.RRateLimiter;
//import org.redisson.api.RateIntervalUnit;
//import org.redisson.api.RateType;
//import org.redisson.api.RedissonClient;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//
///**
// * 专门提供RedisLimiter限流服务（提供通用能力）
// */
//@Service
//public class RedisLimiterManager {
//
//    @Resource
//    private RedissonClient redissonClient;
//
//    /**
//     * 限流操作
//     *
//     * @param key
//     */
//    public void doRateLimit(String key) {
//        //通过key标识限流的对象
//        RRateLimiter rateLimiter = redissonClient.getRateLimiter(key);
//
//        //每秒最多访问2次
//        rateLimiter.trySetRate(RateType.OVERALL, 1, 1, RateIntervalUnit.SECONDS);
//
//        //每当一个请求来了之后，拿走一个令牌(参数是几代表一次请求拿走多少个令牌)
//        boolean canOp = rateLimiter.tryAcquire(1);
//        if (!canOp) {
//            throw new BusinessException(ErrorCode.TOO_MANY_REQUEST);
//        }
//    }
//}