package com.seu.universe.mapper;

import com.seu.universe.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    void addUser(User user);

    int createUserMessageTable(@Param("tableName") String tableName);

    int dropUserMessageTable(@Param("tableName") String tableName);

    User getUserByEmail(String email);

    User getUserByUserId(long userId);
}
