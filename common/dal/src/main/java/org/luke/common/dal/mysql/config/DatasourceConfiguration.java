package org.luke.common.dal.mysql.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.luke.common.dal.migrations.DBMigrationInfo;
import org.luke.common.dal.migrations.enums.DBReadType;
import org.luke.common.dal.migrations.enums.DBWriteType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfiguration {

    @Bean
    @ConfigurationProperties("datasource.old")
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    @ConfigurationProperties("datasource.new")
    public HikariConfig hikariConfigNewDB() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource(@Qualifier("hikariConfig") HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public DataSource dataSourceNewDB(@Qualifier("hikariConfigNewDB") HikariConfig hikariConfig) {
        return new HikariDataSource(hikariConfig);
    }

    @Bean
    public DBMigrationInfo dbMigrationInfo() {
        DBMigrationInfo info = new DBMigrationInfo();
        info.setReadType(DBReadType.OLD);
        info.setWriteType(DBWriteType.OLD);
        return info;
    }
}
