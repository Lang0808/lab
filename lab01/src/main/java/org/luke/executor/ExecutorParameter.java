package org.luke.executor;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.*;

@Getter
@Setter
public class ExecutorParameter {
    private int corePoolSize;
    private int maxPoolSize;
    private long keepAliveTime;
    private int workQueueSize;
}
