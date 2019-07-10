package com.example.shardingjdbcdemo.web.mapper;

import com.example.shardingjdbcdemo.web.entity.AddressInfoEntity;
import com.example.shardingjdbcdemo.web.response.AddressInfo_testEntity;
import com.example.shardingjdbcdemo.web.response.OrderAndAddressResponse;
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

    List<AddressInfo_testEntity> queryByUserId(Long userId);

    List<OrderAndAddressResponse> queryByJoin(Long userId);

    void deleteAll();
}