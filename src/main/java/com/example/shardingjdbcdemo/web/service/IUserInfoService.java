package com.example.shardingjdbcdemo.web.service;

import com.example.shardingjdbcdemo.common.ServerResponse;
import com.example.shardingjdbcdemo.web.entity.UserInfoEntity;

public interface IUserInfoService {

    ServerResponse insert(UserInfoEntity userInfoEntity);

    ServerResponse delete(long id);

    ServerResponse deleteAll();

    ServerResponse insertBatch(int n);

    ServerResponse query(long id);
}
