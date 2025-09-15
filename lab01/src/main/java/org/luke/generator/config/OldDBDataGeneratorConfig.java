package org.luke.generator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("generator.old")
public class OldDBDataGeneratorConfig {
    private int user;
    private int product;
    private int transaction;

    /**
     * how many transaction is created at 1 time
     * this field define the concurrency of generate process
     */
    private int concurrency;
}
