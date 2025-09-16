package org.luke.common.dal.mysql.config;

import org.apache.ibatis.session.SqlSessionFactory;
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
        sqlSessionFactoryRef = "sqlSessionFactory")
//@MapperScan(
//        basePackages = "org.luke.common.dal.mysql.mapper.**",
//        sqlSessionFactoryRef = "sqlSessionFactoryNewDB",
//        nameGenerator = "")
public class MapperConfiguration {

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                 .getResources("classpath:mysql/mapper/*.xml"));
        return sessionFactory.getObject();
    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactoryNewDB(@Qualifier("dataSourceNewDB") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource);
//        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
//                .getResources("classpath:mysql/mapper/*.xml"));
//        return sessionFactory.getObject();
//    }

}
