package com.example.shardingjdbcdemo.controller;

import com.example.shardingjdbcdemo.common.ServerResponse;
import com.example.shardingjdbcdemo.entity.OrderInfoEntity;
import com.example.shardingjdbcdemo.mapper.OrderInfoEntityMapper;
import com.example.shardingjdbcdemo.mapper.TestMapper;
import com.example.shardingjdbcdemo.request.ordersRequest;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @ApiOperation(value = "queryOrdersByUserIdAndSex")
    @RequestMapping(value = "queryOrdersByUserIdAndSex", method = RequestMethod.POST)
    public ServerResponse queryOrdersByUserIdAndSex(@RequestBody ordersRequest request) {
        List result = testMapper.queryOrdersByUserIdAndSex(request);
        if (result == null) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(result);
    }

    @ApiOperation(value = "queryAvgBySex")
    @RequestMapping(value = "queryAvgBySex", method = RequestMethod.GET)
    public ServerResponse queryAvgBySex(@RequestParam int sex) {
        Long result = testMapper.queryAvgBySex(sex);
        if (result == null) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(result);
    }

    @ApiOperation(value = "limit")
    @RequestMapping(value = "limit", method = RequestMethod.GET)
    public ServerResponse limit() {
        List result = testMapper.limit();
        if (result == null) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(result);
    }
}
