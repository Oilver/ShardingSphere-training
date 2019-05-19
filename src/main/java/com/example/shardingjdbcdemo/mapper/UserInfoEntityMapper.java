package com.example.shardingjdbcdemo.mapper;

import com.example.shardingjdbcdemo.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserInfoEntityMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(UserInfoEntity record);

    int insertSelective(UserInfoEntity record);

    UserInfoEntity selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(UserInfoEntity record);

    int updateByPrimaryKey(UserInfoEntity record);

    void deleteAll();

    UserInfoEntity selectByIdAndSex(@Param("userId") long userId,
                                    @Param("sex") int sex);
}