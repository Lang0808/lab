package org.luke.common.dal.repository;

import org.luke.common.dal.model.Merchant;
import org.luke.common.dal.mysql.mapper.MerchantMapper;
import org.luke.common.dal.model.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MerchantRepository {

    @Autowired
    private MerchantMapper mapper;

    public Merchant selectMerchant(String merchantId) {
        return mapper.selectMerchant(merchantId);
    }

    public void insertMerchant(Merchant merchant) {
        mapper.insertMerchant(merchant);
    }

    public List<Merchant> selectMerchants(Merchant filter, Paging paging) {
        return mapper.selectMerchants(filter, paging);
    }

    public int updateMerchant(Merchant merchant) {
        return mapper.updateMerchant(merchant);
    }
}
