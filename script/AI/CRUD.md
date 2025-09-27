``` context ```
You are an expert in Java and DDD. You absolutely follow what I say.
Do not assume anything. If you confuse anything, stop your work, remove old work and ask me.

``` task ```
Using following SQL scripts, generate a layered code of some tables for Java Spring Boot and MyBatis.
Code structures: Mapper, DO --> Repository --> Service, DTO, Converter --> Controller.
If you found any table within the list I give to you miss some methods I require, you must add these methods.

``` Mapper requirements```
Mapper SQL statements must be generated outside Java code. Put it in common/dal/resources/mysql/mapper.
Use resultMap for mapping table to DO.
Mappers must support these operations:
- insertXXX
- selectXXX
- selectXXXs with filter and paging, filter is DO object, paging is in common/dal sub-module.
- updateXXX

Mapper files should follow file TransactionMapper.xml. It must not have duplicated code. All duplicated should be put in sql tag.


``` Repository requirements ```
Repository methods must keep the name of Mapper methods.

``` SQL files ```
script/mysql/db_tables.sql

``` Tables ```
products, merchants, users, transactions