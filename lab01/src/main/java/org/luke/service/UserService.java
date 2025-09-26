package org.luke.service;

import org.luke.common.dal.model.User;
import org.luke.common.dal.repository.UserRepository;
import org.luke.common.model.exception.ErrorCode;
import org.luke.model.req.BuyProductReq;
import org.luke.web.exception.model.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserAndCheck (BuyProductReq req) {
        // check user conditions
        User user = userRepository.selectUser(req.getUserId());
        if (user == null) {
            throw new BaseException(ErrorCode.DATA_NOT_EXIST, "User does not exist");
        }
        long total = Long.parseLong(req.getPrice()) * req.getQuantity();
        if (user.getBalance() < total) {
            throw new BaseException(ErrorCode.INVALID_PARAMS, "User balance is not enough");
        }

        return user;
    }
}
