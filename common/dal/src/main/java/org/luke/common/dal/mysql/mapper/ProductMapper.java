package org.luke.common.dal.mysql.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.luke.common.dal.model.Paging;
import org.luke.common.dal.model.Product;

import java.util.List;

@Mapper
public interface ProductMapper {
    int insertProduct(Product product);
    Product selectProduct(@Param("prodId") String prodId);
    List<Product> selectProducts(@Param("filter") Product filter, @Param("paging") Paging paging);
    int updateProduct(Product product);
}
