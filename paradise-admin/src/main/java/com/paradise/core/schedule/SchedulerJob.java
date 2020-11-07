package com.paradise.core.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Paradise
 */
@Slf4j
@Component
@Profile({"test", "dev"})
public class SchedulerJob {

    @Scheduled(cron = "1 0 0 1/1 * *")
    public void resetWorkerOrderCount() {
        log.info("每日零点：开始重置会员订单数量");
    }

    @Scheduled(cron = "0 0 0 1/1 * *")
    public void resetWorkerLevel() {
        log.info("每日零点：开始重置会员等级");
    }
}
