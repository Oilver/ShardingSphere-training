package com.example.shardingjdbcdemo.web.mapper;

import com.example.shardingjdbcdemo.web.entity.UserInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    List<UserInfoEntity> queryAll();
}