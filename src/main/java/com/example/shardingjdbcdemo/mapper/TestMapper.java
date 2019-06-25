package com.example.shardingjdbcdemo.mapper;

import com.example.shardingjdbcdemo.request.ordersRequest;
import com.example.shardingjdbcdemo.response.UserInfoResponse;
import com.example.shardingjdbcdemo.response.ordersResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    List<ordersResponse> queryOrdersByUserIdAndSex(ordersRequest request);

    Long queryAvgBySex(int sex);

    List<UserInfoResponse> limit();

    int count();
}
