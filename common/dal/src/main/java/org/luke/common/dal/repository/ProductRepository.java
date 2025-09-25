package org.luke.common.dal.repository;

import org.luke.common.dal.model.Paging;
import org.luke.common.dal.model.Product;
import org.luke.common.dal.mysql.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final ProductMapper mapper;

    public ProductRepository(ProductMapper mapper) {
        this.mapper = mapper;
    }

    public int insertProduct(Product product) { return mapper.insertProduct(product); }
    public Product selectProduct(String id) { return mapper.selectProduct(id); }
    public List<Product> selectProducts(Product filter, Paging paging) { return mapper.selectProducts(filter, paging); }
    public int updateProduct(Product product) { return mapper.updateProduct(product); }
}