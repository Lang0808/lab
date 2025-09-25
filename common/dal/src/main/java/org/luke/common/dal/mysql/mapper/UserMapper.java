package org.luke.common.dal.mysql.mapper;

import org.luke.common.dal.model.User;
import org.luke.common.dal.model.Paging;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UserMapper {
    int insertUser(User user);
    User selectUser(@Param("userId") String userId);
    List<User> selectUsers(@Param("filter") User filter, @Param("paging") Paging paging);
    int updateUser(User user);
}
