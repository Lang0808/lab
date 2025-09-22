package org.luke.common.dal.migrations;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.luke.common.dal.migrations.enums.DBReadType;
import org.luke.common.dal.migrations.enums.DBWriteType;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;
import java.util.Map;

/**
 * this class will be injected to {@link org.apache.ibatis.binding.MapperProxy} of all {@link org.apache.ibatis.annotations.Mapper} interface that annotated by {@link PerformDBMigration}
 */
@Setter
@Slf4j
public class MigrateDBSqlSessionTemplate extends SqlSessionTemplate {

    private volatile DBMigrationInfo migrationInfo;

    private SqlSessionTemplate newSqlSessionTemplate;

    public MigrateDBSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }


    @Override
    public <T> T selectOne(String statement) {
        log.info("selectOne1 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("selectOne1 - old db is read");
            return super.selectOne(statement);
        }
        log.info("selectOne1 - new db is read");
        return newSqlSessionTemplate.selectOne(statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        log.info("selectOne2 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("selectOne2 - old db is read");
            return super.selectOne(statement, parameter);
        }
        log.info("selectOne2 - new db is read");
        return newSqlSessionTemplate.selectOne(statement, parameter);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        log.info("selectMap1 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("selectMap1 - old db is read");
            return super.selectMap(statement, mapKey);
        }
        log.info("selectMap1 - new db is read");
        return newSqlSessionTemplate.selectMap(statement, mapKey);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        log.info("selectMap2 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("selectMap2 - old db is read");
            return super.selectMap(statement, parameter, mapKey);
        }
        log.info("selectMap2 - new db is read");
        return newSqlSessionTemplate.selectMap(statement, parameter, mapKey);
    }

    @Override
    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        log.info("selectMap3 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("selectMap3 - old db is read");
            return super.selectMap(statement, parameter, mapKey, rowBounds);
        }
        log.info("selectMap3 - new db is read");
        return newSqlSessionTemplate.selectMap(statement, parameter, mapKey, rowBounds);
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement) {
        log.info("selectCursor1 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("selectCursor1 - old db is read");
            return super.selectCursor(statement);
        }
        log.info("selectCursor1 - new db is read");
        return newSqlSessionTemplate.selectCursor(statement);
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement, Object parameter) {
        log.info("selectCursor2 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("selectCursor2 - old db is read");
            return super.selectCursor(statement, parameter);
        }
        log.info("selectCursor2 - new db is read");
        return newSqlSessionTemplate.selectCursor(statement, parameter);
    }

    @Override
    public <T> Cursor<T> selectCursor(String statement, Object parameter, RowBounds rowBounds) {
        log.info("selectCursor3 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("selectCursor3 - old db is read");
            return super.selectCursor(statement, parameter, rowBounds);
        }
        log.info("selectCursor3 - new db is read");
        return newSqlSessionTemplate.selectCursor(statement, parameter, rowBounds);
    }

    @Override
    public <E> List<E> selectList(String statement) {
        log.info("selectList1 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("selectList1 - old db is read");
            return super.selectList(statement);
        }
        log.info("selectList1 - new db is read");
        return newSqlSessionTemplate.selectList(statement);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        log.info("selectList2 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("selectList2 - old db is read");
            return super.selectList(statement, parameter);
        }
        log.info("selectList2 - new db is read");
        return newSqlSessionTemplate.selectList(statement, parameter);
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        log.info("selectList3 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("selectList3 - old db is read");
            return super.selectList(statement, parameter, rowBounds);
        }
        log.info("selectList3 - new db is read");
        return newSqlSessionTemplate.selectList(statement, parameter, rowBounds);
    }

    @Override
    public void select(String statement, ResultHandler handler) {
        log.info("select1 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("select1 - old db is read");
            super.select(statement, handler);
        } else {
            log.info("select1 - new db is read");
            newSqlSessionTemplate.select(statement, handler);
        }
    }

    @Override
    public void select(String statement, Object parameter, ResultHandler handler) {
        log.info("select2 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("select2 - old db is read");
            super.select(statement, parameter, handler);
        } else {
            log.info("select2 - new db is read");
            newSqlSessionTemplate.select(statement, parameter, handler);
        }
    }

    @Override
    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        log.info("select3 - migrationInfo = {}", migrationInfo);
        if (migrationInfo.getReadType().equals(DBReadType.OLD)) {
            log.info("select3 - old db is read");
            super.select(statement, parameter, rowBounds, handler);
        } else {
            log.info("select3 - new db is read");
            newSqlSessionTemplate.select(statement, parameter, rowBounds, handler);
        }
    }


    @Override
    public int insert(String statement) {
        log.info("insert - writeType = {}", migrationInfo.getWriteType());

        if (migrationInfo.getWriteType() == DBWriteType.OLD) {
            log.info("insert - old db is written");
            return super.insert(statement);
        } else if (migrationInfo.getWriteType() == DBWriteType.NEW) {
            log.info("insert - new db is written");
            return newSqlSessionTemplate.insert(statement);
        } else {
            log.info("insert - both dbs are written");
            int oldResult = super.insert(statement);
            newSqlSessionTemplate.insert(statement);
            return oldResult;
        }
    }

    @Override
    public int insert(String statement, Object parameter) {
        log.info("insert2 - writeType = {}", migrationInfo.getWriteType());

        if (migrationInfo.getWriteType() == DBWriteType.OLD) {
            log.info("insert2 - old db is written");
            return super.insert(statement, parameter);
        } else if (migrationInfo.getWriteType() == DBWriteType.NEW) {
            log.info("insert2 - new db is written");
            return newSqlSessionTemplate.insert(statement, parameter);
        } else {
            log.info("insert2 - both dbs are written");
            int oldResult = super.insert(statement, parameter);
            newSqlSessionTemplate.insert(statement, parameter);
            return oldResult;
        }
    }

    @Override
    public int update(String statement) {
        log.info("update - writeType = {}", migrationInfo.getWriteType());

        if (migrationInfo.getWriteType() == DBWriteType.OLD) {
            log.info("update - old db is written");
            return super.update(statement);
        } else if (migrationInfo.getWriteType() == DBWriteType.NEW) {
            log.info("update - new db is written");
            return newSqlSessionTemplate.update(statement);
        } else {
            log.info("update - both dbs are written");
            int oldResult = super.update(statement);
            newSqlSessionTemplate.update(statement);
            return oldResult;
        }
    }

    @Override
    public int update(String statement, Object parameter) {
        log.info("update2 - writeType = {}", migrationInfo.getWriteType());

        if (migrationInfo.getWriteType() == DBWriteType.OLD) {
            log.info("update2 - old db is written");
            return super.update(statement, parameter);
        } else if (migrationInfo.getWriteType() == DBWriteType.NEW) {
            log.info("update2 - new db is written");
            return newSqlSessionTemplate.update(statement, parameter);
        } else {
            log.info("update2 - both dbs are written");
            int oldResult = super.update(statement, parameter);
            newSqlSessionTemplate.update(statement, parameter);
            return oldResult;
        }
    }

    @Override
    public int delete(String statement) {
        log.info("delete - writeType = {}", migrationInfo.getWriteType());

        if (migrationInfo.getWriteType() == DBWriteType.OLD) {
            log.info("delete - old db is written");
            return super.delete(statement);
        } else if (migrationInfo.getWriteType() == DBWriteType.NEW) {
            log.info("delete - new db is written");
            return newSqlSessionTemplate.delete(statement);
        } else {
            log.info("delete - both dbs are written");
            int oldResult = super.delete(statement);
            newSqlSessionTemplate.delete(statement);
            return oldResult;
        }
    }

    @Override
    public int delete(String statement, Object parameter) {
        log.info("delete2 - writeType = {}", migrationInfo.getWriteType());

        if (migrationInfo.getWriteType() == DBWriteType.OLD) {
            log.info("delete2 - old db is written");
            return super.delete(statement, parameter);
        } else if (migrationInfo.getWriteType() == DBWriteType.NEW) {
            log.info("delete2 - new db is written");
            return newSqlSessionTemplate.delete(statement, parameter);
        } else {
            log.info("delete2 - both dbs are written");
            int oldResult = super.delete(statement, parameter);
            newSqlSessionTemplate.delete(statement, parameter);
            return oldResult;
        }
    }


    @Override
    public void clearCache() {
        super.clearCache();
        newSqlSessionTemplate.clearCache();
    }
}
