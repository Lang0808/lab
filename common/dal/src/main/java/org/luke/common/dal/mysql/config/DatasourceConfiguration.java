package org.luke.common.dal.mysql.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {

    @Bean
    @ConfigurationProperties("datasource.mysql")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

//    @Bean
//    @ConfigurationProperties("datasource.new.mysql")
//    public HikariConfig hikariConfigNewDB() {
//        return new HikariConfig();
//    }

    @Bean
    public DataSource dataSource(@Qualifier("hikariConfig") HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

//    @Bean
//    public DataSource dataSourceNewDB(@Qualifier("hikariConfigNewDB")HikariConfig hikariConfig) {
//        return new HikariDataSource(hikariConfig);
//    }
}
