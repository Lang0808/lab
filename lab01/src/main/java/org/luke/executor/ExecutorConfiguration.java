package org.luke.executor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

@Configuration
public class ExecutorConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "generator.executor")
    public ExecutorParameter generatorExecutorParameter() {
        return new ExecutorParameter();
    }

    @Bean
    public ThreadFactory generatorExecutorThreadFactory() {
        CustomizableThreadFactory threadFactory = new CustomizableThreadFactory("generatorExecutorThreadFactory-");
        threadFactory.setThreadGroupName("generatorExecutorThreadFactory");
        return threadFactory;
    }

    @Bean
    public Executor generatorExecutor(
            @Qualifier("generatorExecutorParameter") ExecutorParameter parameter,
            @Qualifier("generatorExecutorThreadFactory") ThreadFactory threadFactory) {
        return new ThreadPoolExecutor(
                parameter.getCorePoolSize(),
                parameter.getMaxPoolSize(),
                parameter.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(parameter.getWorkQueueSize()),
                threadFactory,
                new ThreadPoolExecutor.DiscardPolicy()
        );
    }
}
