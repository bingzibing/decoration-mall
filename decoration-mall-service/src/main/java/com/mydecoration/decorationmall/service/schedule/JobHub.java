package com.mydecoration.decorationmall.service.schedule;

import com.mydecoration.decorationmall.service.schedule.jobs.UpdateTestStatTask;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

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

    @Autowired
    private UpdateTestStatTask updateTestStatTask;

    @Scheduled(cron = "${cron.updateTestStatTask}")
    public void scheduleCreateBuildPlanJob() {
        scheduleJob("updateTestStatTask", TIME_TO_ALIVE, updateTestStatTask);
    }

    /**
     * 调度任务
     *
     * @param jobName
     *            任务名称
     * @param redisKeyAlive
     *            redisKey有效时间，为了防止多实例同时调度同一个任务，让其中某个示例获取redis锁，其他实例拿不到锁就不执行，
     *            为了防止多个实例机器的时间有稍微偏差，让redis的key先live一段时间，错过了机器时间误差即可
     * @param job
     *            任务实例
     */
    private void scheduleJob(String jobName, long redisKeyAlive, Runnable job) {
        String key = String.format(KEY_FORMATTER, jobName);
        if (stringRedisTemplate.opsForValue().setIfAbsent(key, OK, redisKeyAlive, TimeUnit.MILLISECONDS)) {
            try {
                job.run();
            } catch (Exception e) {
                log.error("run job encountered exception", e);
            }
        } else {
            log.warn("get lock failed for schedule job:{}", jobName);
            return;
        }
    }
}
