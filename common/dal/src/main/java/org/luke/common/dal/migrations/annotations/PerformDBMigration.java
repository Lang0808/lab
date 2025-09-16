package org.luke.common.dal.migrations.annotations;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
public @interface PerformDBMigration {
    String oldDataSourceRef();
    String newDataSourceRef();
}
