package org.luke.common.dal.migrations.factory;

import org.luke.common.dal.migrations.annotations.PerformDBMigration;
import org.luke.common.dal.migrations.model.DBMigrationInfo;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.sql.DataSource;

public class MigrateDBMapperFactoryBean<T> extends MapperFactoryBean<T> implements ApplicationContextAware {

    @Autowired
    private DBMigrationInfo migrationInfo;

    private DataSource oldDataSource;
    private DataSource newDataSource;

    private ApplicationContext applicationContext;

    public MigrateDBMapperFactoryBean() {
        super();
    }

    public MigrateDBMapperFactoryBean(Class<T> mapperInterface) {
        super(mapperInterface);
        processPerformDBMigration(mapperInterface);
    }

    private void processPerformDBMigration(Class<T> mapperInterface) {
        if (mapperInterface.isAnnotationPresent(PerformDBMigration.class)) {
            PerformDBMigration performDbMigration = mapperInterface.getAnnotation(PerformDBMigration.class);
            oldDataSource = applicationContext.getBean(performDbMigration.oldDataSourceRef(), DataSource.class);
            newDataSource = applicationContext.getBean(performDbMigration.newDataSourceRef(), DataSource.class);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setMapperInterface(Class<T> mapperInterface) {
        super.setMapperInterface(mapperInterface);
        processPerformDBMigration(mapperInterface);
    }
}
