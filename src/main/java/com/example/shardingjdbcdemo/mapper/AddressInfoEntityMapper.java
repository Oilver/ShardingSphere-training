package com.example.shardingjdbcdemo.mapper;

import com.example.shardingjdbcdemo.entity.AddressInfoEntity;
import com.example.shardingjdbcdemo.response.OrderAndAddressResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AddressInfoEntityMapper {
    int deleteByPrimaryKey(Long addressId);

    int insert(AddressInfoEntity record);

    int insertSelective(AddressInfoEntity record);

    AddressInfoEntity selectByPrimaryKey(Long addressId);

    int updateByPrimaryKeySelective(AddressInfoEntity record);

    int updateByPrimaryKey(AddressInfoEntity record);

    /**
     * ---------test non-sharding methods---------
     *
     * @param userId
     */
    void deleteByUserId(long userId);

    void updateByUserId(AddressInfoEntity record);

    AddressInfoEntity queryByUserId(Long userId);

    List<OrderAndAddressResponse> queryByJoin(Long userId);

    void deleteAll();
}