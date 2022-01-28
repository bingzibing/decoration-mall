package com.mydecoration.decorationmall.service.schedule;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Log4j2
@EnableScheduling
@Component
public class JobHub {

    private static final String KEY_FORMATTER = "ota-cron-service:job:%s";

    private static final String OK = "ok";

    /**
     * 300秒
     */
    private static final long TIME_TO_ALIVE = 30 * 1000L;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
}
