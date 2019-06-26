package com.example.shardingjdbcdemo.web.controller;

import com.example.shardingjdbcdemo.common.ServerResponse;
import com.example.shardingjdbcdemo.web.entity.UserInfoEntity;
import com.example.shardingjdbcdemo.web.mapper.UserInfoEntityMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserInfoController {

    @Autowired
    private UserInfoEntityMapper userInfoMapper;

    @ApiOperation(value = "插入一条记录")
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public ServerResponse insert(@RequestBody UserInfoEntity userInfoEntity) {
        int result = userInfoMapper.insert(userInfoEntity);
        if (result == 0) {
            return ServerResponse.createByErrorMessage("新增失败");
        }
        return ServerResponse.createBySuccess("新增成功");
    }

    @ApiOperation(value = "删除一条记录")
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ServerResponse delete(@RequestParam long id) {
        int result = userInfoMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            return ServerResponse.createByErrorMessage("删除失败");
        }
        return ServerResponse.createBySuccess("删除成功");
    }

    @ApiOperation(value = "删除全部记录")
    @RequestMapping(value = "deleteAll", method = RequestMethod.POST)
    public ServerResponse deleteAll() {
        try {
            userInfoMapper.deleteAll();
        } catch (Exception e) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess();
    }

    @ApiOperation(value = "增加n条记录记录")
    @RequestMapping(value = "insertBatch", method = RequestMethod.POST)
    public ServerResponse insertBatch(@RequestParam int n) {
        try {
            for (int i = 0; i < n; i++) {
                UserInfoEntity entity = new UserInfoEntity();
//                entity.setUserId(Integer.toUnsignedLong(i));
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

    @ApiOperation(value = "query")
    @RequestMapping(value = "query", method = RequestMethod.GET)
    public ServerResponse query(@RequestParam long id) {
        UserInfoEntity entity = userInfoMapper.selectByPrimaryKey(id);
        if (null == entity) {
            return ServerResponse.createByErrorMessage("查询失败");
        }
        return ServerResponse.createBySuccess(entity);
    }

    @ApiOperation(value = "queryByEntity")
    @RequestMapping(value = "queryByEntity", method = RequestMethod.POST)
    public ServerResponse queryByEntity(@RequestParam long id, @RequestParam int sex) {
        return ServerResponse.createBySuccess(userInfoMapper.selectByIdAndSex(id, sex));
    }

    @ApiOperation(value = "queryAll")
    @RequestMapping(value = "queryAll", method = RequestMethod.POST)
    public ServerResponse queryAll() {
        List result = userInfoMapper.queryAll();
        if (result == null) {
            return ServerResponse.createByError();
        }
        return ServerResponse.createBySuccess(result);
    }

    @ApiOperation(value = "增加n条记录记录")
    @RequestMapping(value = "insertBatchByJDBC", method = RequestMethod.POST)
    public ServerResponse insertBatchByJDBC() {

        Connection conn = null;
        PreparedStatement pstm = null;
        String url = "jdbc:mysql://localhost:3306/user?useServerPrepStmts=false&rewriteBatchedStatements=true&useUnicode=true;characterEncoding=UTF8";
        String user = "root";
        String password = "admin";

        try {
            //加载jdbc驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            //连接mysql
            conn = DriverManager.getConnection(url, user, password);
            //将自动提交关闭
            conn.setAutoCommit(false);
            //编写sql
            String sql = "insert into user_info(user_name,account,password,sex) VALUES (?,?,?,?)";
            //预编译sql
            pstm = conn.prepareStatement(sql);
            //开始总计时
            long bTime1 = System.currentTimeMillis();

            //循环10次，每次十万数据，一共1800万
            for (int i = 0; i < 180; i++) {

                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                for (int j = 0; j < 100000; j++) {
                    //赋值
                    pstm.setString(1, "testName");
                    pstm.setString(2, "testAccount");
                    pstm.setString(3, "testPassword");
                    pstm.setInt(4, Math.random() > 0.4 ? 1 : 0);
                    //添加到同一个批处理中
                    pstm.addBatch();
                }
                //执行批处理
                pstm.executeBatch();
                //提交事务
                conn.commit();
                //关闭分段计时
                long eTime = System.currentTimeMillis();
                //输出
                System.out.println("成功插入10W条数据耗时：" + (eTime - bTime));
            }
            //关闭总计时
            long eTime1 = System.currentTimeMillis();
            //输出
            System.out.println("插入1800W数据共耗时：" + (eTime1 - bTime1));
            return ServerResponse.createBySuccess("插入1800W数据共耗时: " + (eTime1 - bTime1));

        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
}
