package com.example.shardingjdbcdemo.mapper;

import com.example.shardingjdbcdemo.entity.OrderInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoEntityMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(OrderInfoEntity record);

    int insertSelective(OrderInfoEntity record);

    OrderInfoEntity selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(OrderInfoEntity record);

    int updateByPrimaryKey(OrderInfoEntity record);
}