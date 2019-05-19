package com.example.shardingjdbcdemo.service;

import com.example.shardingjdbcdemo.common.ServerResponse;
import com.example.shardingjdbcdemo.entity.UserInfoEntity;

public interface IUserInfoService {

    ServerResponse insert(UserInfoEntity userInfoEntity);

    ServerResponse delete(long id);

    ServerResponse deleteAll();

    ServerResponse insertBatch(int n);

    ServerResponse query(long id);
}
