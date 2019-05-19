package com.example.shardingjdbcdemo.controller;

import com.example.shardingjdbcdemo.common.ServerResponse;
import com.example.shardingjdbcdemo.entity.UserInfoEntity;
import com.example.shardingjdbcdemo.mapper.UserInfoEntityMapper;
import com.example.shardingjdbcdemo.service.IUserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserInfoController {

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private UserInfoEntityMapper userInfoEntityMapper;

    @ApiOperation(value = "插入一条记录")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ServerResponse insert(@RequestBody UserInfoEntity userInfoEntity) {
        return userInfoService.insert(userInfoEntity);
    }

    @ApiOperation(value = "删除一条记录")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ServerResponse delete(@RequestParam long id) {
        return userInfoService.delete(id);
    }

    @ApiOperation(value = "删除全部记录")
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    public ServerResponse deleteAll() {
        return userInfoService.deleteAll();
    }

    @ApiOperation(value = "增加n条记录记录")
    @RequestMapping(value = "insertBatch", method = RequestMethod.POST)
    public ServerResponse insertBatch(@RequestParam int n) {
        return userInfoService.insertBatch(n);
    }

    @ApiOperation(value = "query")
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public ServerResponse query(@RequestParam long id) {
        return userInfoService.query(id);
    }

    @ApiOperation(value = "queryByEntity")
    @RequestMapping(value = "queryByEntity", method = RequestMethod.POST)
    public ServerResponse queryByEntity(@RequestParam long id, @RequestParam int sex) {
        return ServerResponse.createBySuccess(userInfoEntityMapper.selectByIdAndSex(id, sex));
    }
}
