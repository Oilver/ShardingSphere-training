package com.example.shardingjdbcdemo.controller;

import com.example.shardingjdbcdemo.common.ServerResponse;
import com.example.shardingjdbcdemo.entity.OrderInfoEntity;
import com.example.shardingjdbcdemo.entity.UserInfoEntity;
import com.example.shardingjdbcdemo.mapper.OrderInfoEntityMapper;
import com.example.shardingjdbcdemo.service.IUserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderInfoController {

    @Autowired
    private OrderInfoEntityMapper orderInfoEntityMapper;

    @ApiOperation(value = "插入一条记录")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ServerResponse insert(@RequestBody OrderInfoEntity orderInfoEntity) {
        int result = orderInfoEntityMapper.insertSelective(orderInfoEntity);
        if (result == 0) {
            return ServerResponse.createByErrorMessage("插入失败");
        }
        return ServerResponse.createBySuccess("插入成功");
    }

}
