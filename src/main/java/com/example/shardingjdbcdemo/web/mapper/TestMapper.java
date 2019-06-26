package com.example.shardingjdbcdemo.web.mapper;

import com.example.shardingjdbcdemo.web.request.OrdersRequest;
import com.example.shardingjdbcdemo.web.response.OrdersResponse;
import com.example.shardingjdbcdemo.web.response.UserInfoResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    List<OrdersResponse> queryOrdersByUserIdAndSex(OrdersRequest request);

    Long queryAvgBySex(int sex);

    List<UserInfoResponse> limit();

    int count();
}
