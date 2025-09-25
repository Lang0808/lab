package org.luke.common.dal.mysql.mapper;

import org.luke.common.dal.model.Merchant;
import org.luke.common.dal.model.Paging;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MerchantMapper {
    int insertMerchant(Merchant merchant);
    Merchant selectMerchant(@Param("merchantId") String merchantId);
    List<Merchant> selectMerchants(@Param("filter") Merchant filter, @Param("paging") Paging paging);
    int updateMerchant(Merchant merchant);
}
