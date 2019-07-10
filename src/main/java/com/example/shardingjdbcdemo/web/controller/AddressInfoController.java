package com.example.shardingjdbcdemo.web.controller;

import com.example.shardingjdbcdemo.common.ServerResponse;
import com.example.shardingjdbcdemo.web.entity.AddressInfoEntity;
import com.example.shardingjdbcdemo.web.mapper.AddressInfoEntityMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("address")
public class AddressInfoController {

    @Autowired
    private AddressInfoEntityMapper addressInfoEntityMapper;

    @ApiOperation(value = "插入一条记录")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ServerResponse insert(@RequestBody AddressInfoEntity addressInfoEntity) {
        int result = addressInfoEntityMapper.insertSelective(addressInfoEntity);
        if (result == 0) {
            return ServerResponse.createByErrorMessage("插入失败");
        }
        return ServerResponse.createBySuccess("插入成功");
    }

    @ApiOperation(value = "deleteByUserId")
    @RequestMapping(value = "deleteByUserId", method = RequestMethod.POST)
    public ServerResponse deleteByUserId(@RequestParam long userId) {
        addressInfoEntityMapper.deleteByUserId(userId);
        return ServerResponse.createBySuccess("删除成功");
    }

    @ApiOperation(value = "updateByUserId")
    @RequestMapping(value = "updateByUserId", method = RequestMethod.POST)
    public ServerResponse updateByUserId(@RequestBody AddressInfoEntity addressInfoEntity) {
        addressInfoEntityMapper.updateByUserId(addressInfoEntity);
        return ServerResponse.createBySuccess("插入成功");
    }

    @ApiOperation(value = "queryByUserId")
    @RequestMapping(value = "queryByUserId", method = RequestMethod.POST)
    public ServerResponse queryByUserId(@RequestParam long userId) {
        List result = addressInfoEntityMapper.queryByUserId(userId);
        return ServerResponse.createBySuccess(result);
    }

    @ApiOperation(value = "queryByJoin")
    @RequestMapping(value = "queryByJoin", method = RequestMethod.POST)
    public ServerResponse queryByJoin(@RequestParam long userId) {
        List result = addressInfoEntityMapper.queryByJoin(userId);
        return ServerResponse.createBySuccess(result);
    }

    @ApiOperation(value = "deleteAll")
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    public ServerResponse deleteAll() {
        addressInfoEntityMapper.deleteAll();
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "增加n条记录记录")
    @RequestMapping(value = "insertBatch", method = RequestMethod.POST)
    public ServerResponse insertBatch(@RequestParam int n) {
        try {
            for (int i = 0; i < n; i++) {
                AddressInfoEntity entity = new AddressInfoEntity();
                entity.setUserId(Integer.toUnsignedLong(i));
                entity.setAddressName(Math.random() > 0.4 ? "汕头" : "东莞");
                addressInfoEntityMapper.insertSelective(entity);

            }
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }
}
