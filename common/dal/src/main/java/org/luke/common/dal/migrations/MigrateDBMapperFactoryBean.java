package org.luke.common.dal.migrations;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * This class is responsible for creating {@link org.apache.ibatis.binding.MapperProxy}.
 * Change SqlSession that is injected to {@link org.apache.ibatis.binding.MapperProxy} from {@link SqlSessionTemplate} to {@link MigrateDBSqlSessionTemplate}.
 *
 * @param <T>
 */
public class MigrateDBMapperFactoryBean<T> extends MapperFactoryBean<T> {

    @Autowired
    private volatile DBMigrationInfo migrationInfo;

    @Autowired
    private ApplicationContext applicationContext;

    public MigrateDBMapperFactoryBean() {
        super();
    }

    public MigrateDBMapperFactoryBean(Class<T> mapperInterface) {
        super(mapperInterface);
    }

    @Override
    public void setMapperInterface(Class<T> mapperInterface) {
        super.setMapperInterface(mapperInterface);
    }

    /**
     * Create SqlSessionTemplate that will be injected to {@link org.apache.ibatis.binding.MapperProxy}.
     *
     * @return an instance of {@link SqlSessionTemplate} if interface is not annotated by {@link PerformDBMigration}. Otherwise, return an instance of {@link MigrateDBSqlSessionTemplate}
     */
    @Override
    protected SqlSessionTemplate createSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        Class<T> mapperInterface = getMapperInterface();
        if (!mapperInterface.isAnnotationPresent(PerformDBMigration.class)) {
            return super.createSqlSessionTemplate(sqlSessionFactory);
        }

        PerformDBMigration performDBMigration = mapperInterface.getAnnotation(PerformDBMigration.class);
        SqlSessionFactory newDBSqlSessionFactory = applicationContext.getBean(performDBMigration.newDBSqlSessionFactoryRef(), SqlSessionFactory.class);
        SqlSessionTemplate newDBSqlSessionTemplate = new SqlSessionTemplate(newDBSqlSessionFactory);
        MigrateDBSqlSessionTemplate migrateTemplate = new MigrateDBSqlSessionTemplate(sqlSessionFactory);
        migrateTemplate.setNewSqlSessionTemplate(newDBSqlSessionTemplate);
        migrateTemplate.setMigrationInfo(migrationInfo);
        return migrateTemplate;
    }
}
