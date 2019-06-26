package com.example.shardingjdbcdemo.web.service.impl;

import com.example.shardingjdbcdemo.common.ServerResponse;
import com.example.shardingjdbcdemo.web.entity.UserInfoEntity;
import com.example.shardingjdbcdemo.web.mapper.UserInfoEntityMapper;
import com.example.shardingjdbcdemo.web.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoEntityMapper userInfoMapper;

    @Override
    public ServerResponse insert(UserInfoEntity userInfoEntity) {

        int result = userInfoMapper.insert(userInfoEntity);
        if (result == 0) {
            return ServerResponse.createByErrorMessage("新增失败");
        }
        return ServerResponse.createBySuccess("新增成功");
    }

    @Override
    public ServerResponse delete(long id) {
        int result = userInfoMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccess("删除成功");
    }

    @Override
    public ServerResponse deleteAll() {
        try {
            userInfoMapper.deleteAll();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse insertBatch(int n) {
        try {
            for (int i = 0; i < n; i++) {
                UserInfoEntity entity = new UserInfoEntity();
                entity.setUserId(Integer.toUnsignedLong(i));
                entity.setUserName("testName");
                entity.setAccount("testAccount");
                entity.setPassword("testPassword");
                entity.setSex(Math.random() > 0.4 ? 1 : 0);
                userInfoMapper.insert(entity);
            }
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }

    @Override
    public ServerResponse query(long id) {
        UserInfoEntity entity = userInfoMapper.selectByPrimaryKey(id);
        if (null == entity) {
            return ServerResponse.createByErrorMessage("查询失败");
        }
        return ServerResponse.createBySuccess(entity);
    }
}
