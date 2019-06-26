package com.example.shardingjdbcdemo.web.mapper;

import com.example.shardingjdbcdemo.web.request.ordersRequest;
import com.example.shardingjdbcdemo.web.response.UserInfoResponse;
import com.example.shardingjdbcdemo.web.response.ordersResponse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

    List<ordersResponse> queryOrdersByUserIdAndSex(ordersRequest request);

    Long queryAvgBySex(int sex);

    List<UserInfoResponse> limit();

    int count();
}
