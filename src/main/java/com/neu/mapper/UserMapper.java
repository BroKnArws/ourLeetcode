package com.neu.mapper;

import com.neu.entity.LeetcodeUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    LeetcodeUser selectUserById(Integer creatorId);
}
