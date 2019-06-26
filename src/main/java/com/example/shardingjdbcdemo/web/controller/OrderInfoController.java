package com.example.shardingjdbcdemo.web.controller;


import com.example.shardingjdbcdemo.common.ServerResponse;
import com.example.shardingjdbcdemo.web.entity.OrderInfoEntity;
import com.example.shardingjdbcdemo.web.mapper.OrderInfoEntityMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation(value = "deleteByUserId")
    @RequestMapping(value = "deleteByUserId", method = RequestMethod.POST)
    public ServerResponse deleteByUserId(@RequestParam long userId) {
        orderInfoEntityMapper.deleteByUserId(userId);
        return ServerResponse.createBySuccess("删除成功");
    }

    @ApiOperation(value = "updateByUserId")
    @RequestMapping(value = "updateByUserId", method = RequestMethod.POST)
    public ServerResponse updateByUserId(@RequestBody OrderInfoEntity orderInfoEntity) {
        orderInfoEntityMapper.updateByUserId(orderInfoEntity);
        return ServerResponse.createBySuccess("更新成功");
    }

    @ApiOperation(value = "queryByUserId")
    @RequestMapping(value = "queryByUserId", method = RequestMethod.POST)
    public ServerResponse queryByUserId(@RequestParam long userId) {
        List result = orderInfoEntityMapper.queryByUserId(userId);
        return ServerResponse.createBySuccess(result);
    }

    @ApiOperation(value = "queryByUserIdAndOrderId")
    @RequestMapping(value = "queryByUserIdAndOrderId", method = RequestMethod.POST)
    public ServerResponse queryByUserIdAndOrderId(@RequestParam long userId,@RequestParam long orderId) {
        List result = orderInfoEntityMapper.queryByUserIdAndOrderId(userId,orderId);
        return ServerResponse.createBySuccess(result);
    }

    @ApiOperation(value = "deleteAll")
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    public ServerResponse deleteAll() {
        orderInfoEntityMapper.deleteAll();
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "增加n条记录记录")
    @RequestMapping(value = "insertBatch", method = RequestMethod.POST)
    public ServerResponse insertBatch(@RequestParam int n) {
        try {
            for (int i = 0; i < n; i++) {
                OrderInfoEntity entity = new OrderInfoEntity();
                entity.setUserId(Integer.toUnsignedLong(i));
                entity.setSex(Math.random() > 0.4 ? 1 : 0);
                orderInfoEntityMapper.insertSelective(entity);
            }
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }

}
