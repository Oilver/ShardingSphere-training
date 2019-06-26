package com.example.shardingjdbcdemo.web.mapper;

import com.example.shardingjdbcdemo.web.entity.OrderInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderInfoEntityMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(OrderInfoEntity record);

    int insertSelective(OrderInfoEntity record);

    OrderInfoEntity selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(OrderInfoEntity record);

    int updateByPrimaryKey(OrderInfoEntity record);


    /**
     * ---------test non-sharding methods---------
     *
     * @param userId
     */
    void deleteByUserId(long userId);

    void updateByUserId(OrderInfoEntity record);

    List<OrderInfoEntity> queryByUserId(Long userId);

    List<OrderInfoEntity> queryByUserIdAndOrderId(@Param("userId") long userId,
                                                  @Param("orderId") long orderId);

    void deleteAll();
}