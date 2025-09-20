package org.luke.common.dal.mysql.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.luke.common.dal.interceptors.DALLogPreparedInterceptor;
import org.luke.common.dal.migrations.MigrateDBMapperFactoryBean;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages = "org.luke.common.dal.mysql.mapper.**",
        sqlSessionFactoryRef = "sqlSessionFactory",
        factoryBean = MigrateDBMapperFactoryBean.class)
public class MapperConfiguration {

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                 .getResources("classpath:mysql/mapper/*.xml"));
        sessionFactory.setPlugins(new DALLogPreparedInterceptor());
        return sessionFactory.getObject();
    }

    @Bean
    public SqlSessionFactory sqlSessionFactoryNewDB(@Qualifier("dataSourceNewDB") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mysql/mapper/*.xml"));
        sessionFactory.setPlugins(new DALLogPreparedInterceptor());
        return sessionFactory.getObject();
    }

}
