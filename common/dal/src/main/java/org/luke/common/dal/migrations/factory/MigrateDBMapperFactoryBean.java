package org.luke.common.dal.migrations.factory;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.luke.common.dal.migrations.annotations.PerformDBMigration;
import org.luke.common.dal.migrations.mappers.MigrateDBSqlSessionTemplate;
import org.luke.common.dal.migrations.model.DBMigrationInfo;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * This class is responsible for creating {@link org.apache.ibatis.binding.MapperProxy}
 * @param <T>
 */
public class MigrateDBMapperFactoryBean<T> extends MapperFactoryBean<T> implements ApplicationContextAware {

    @Autowired
    private DBMigrationInfo migrationInfo;

    private ApplicationContext applicationContext;

    private MigrateDBSqlSessionTemplate migrateDBSqlSessionTemplate;

    public MigrateDBMapperFactoryBean() {
        super();
    }

    public MigrateDBMapperFactoryBean(Class<T> mapperInterface) {
        super(mapperInterface);
        initNewDBSqlSessionTemplate(mapperInterface);
    }

    /**
     * this method checks {@link PerformDBMigration} in interface and initialized new sql session template
     * old db sql session template is initialized by {@link org.mybatis.spring.mapper.ClassPathMapperScanner}
     * @param mapperInterface the interface of mapper
     */
    private void initNewDBSqlSessionTemplate(Class<T> mapperInterface) {
        if (mapperInterface.isAnnotationPresent(PerformDBMigration.class)) {
            PerformDBMigration performDbMigration = mapperInterface.getAnnotation(PerformDBMigration.class);
            SqlSessionFactory newSqlSessionFactory = applicationContext.getBean(performDbMigration.newDataSourceRef(), SqlSessionFactory.class);
            SqlSessionTemplate newSqlSessionTemplate = createSqlSessionTemplate(newSqlSessionFactory);

        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setMapperInterface(Class<T> mapperInterface) {
        super.setMapperInterface(mapperInterface);
        initNewDBSqlSessionTemplate(mapperInterface);
    }

    @Override
    public SqlSession getSqlSession() {
        return new MigrateDBSqlSessionTemplate();
    }
}
