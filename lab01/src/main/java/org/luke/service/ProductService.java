package org.luke.service;

import org.luke.common.dal.model.Product;
import org.luke.common.dal.repository.ProductRepository;
import org.luke.common.model.exception.ErrorCode;
import org.luke.model.req.BuyProductReq;
import org.luke.web.exception.model.BaseException;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {

    @Autowired
    private ProductRepository prodRepo;

    /**
     * Get product and check condition.
     * @param req request
     * @return product if all conditions pass
     */
    public Product getProductAndCheck (BuyProductReq req) {
        Product product = prodRepo.selectProduct(req.getProductId());
        if (product == null) {
            throw new BaseException(ErrorCode.DATA_NOT_EXIST, "Product does not exist");
        }
        if (product.getPrice() != Long.parseLong(req.getPrice())) {
            throw new BaseException(ErrorCode.INVALID_PARAMS, "Price maybe changed");
        }
        // TODO: check product quantity in stock

        return product;
    }
}
