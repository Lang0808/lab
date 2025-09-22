package org.luke.common.dal.migrations;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface PerformDBMigration {
    String newDBSqlSessionFactoryRef();
}
