``` context ```
You are an expert in Java and DDD.

``` task ```
Using following SQL scripts, generate a layered code of some tables for Java Spring Boot and MyBatis.
Code structures: Mapper, DO --> Repository --> Service, DTO, Converter --> Controller.

``` Mapper requirements```
Mapper SQL statements must be generated outside of Java code. Put it in common/dal/resources/mysql/mapper.
Use resultMap for mapping table to DO.
Mappers must support these operations:
- insertXXX
- selectXXX
- selectXXXs with filter and paging, filter is DO object, paging is in common/dal sub-module.
- updateXXX
Mapper files should follow file TransactionMapper.xml

``` Repository requirements ```
Repository must keep the name of Mapper.
Repository must follow file TransactionRepository.java

``` Service requirements ```

``` SQL files ```
script/mysql/db_tables.sql

``` Tables ```
products, merchants, users