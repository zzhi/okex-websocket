package com.biup.okex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * Springboot本身默认的执行方式是串行执行，也就是说无论有多少task，都是一个线程串行执行，并行需手动配置
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(10);
        taskRegistrar.setScheduler(scheduled);
    }
}
