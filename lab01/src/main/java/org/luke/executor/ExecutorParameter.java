package org.luke.executor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExecutorParameter {
    private int corePoolSize;
    private int maxPoolSize;
    private long keepAliveTime;
    private int workQueueSize;
}
