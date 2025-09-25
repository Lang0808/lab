package org.luke.common.dal.repository;

import org.luke.common.dal.model.User;
import org.luke.common.dal.mysql.mapper.UserMapper;
import org.luke.common.dal.model.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserRepository {

    @Autowired
    private UserMapper mapper;

    public User selectUser(String userId) {
        return mapper.selectUser(userId);
    }

    public void insertUser(User user) {
        mapper.insertUser(user);
    }

    public List<User> selectUsers(User filter, Paging paging) {
        return mapper.selectUsers(filter, paging);
    }

    public int updateUser(User user) {
        return mapper.updateUser(user);
    }
}
