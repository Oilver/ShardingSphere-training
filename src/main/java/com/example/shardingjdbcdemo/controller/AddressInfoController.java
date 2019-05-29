package com.example.shardingjdbcdemo.controller;

import com.example.shardingjdbcdemo.common.ServerResponse;
import com.example.shardingjdbcdemo.entity.AddressInfoEntity;
import com.example.shardingjdbcdemo.mapper.AddressInfoEntityMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
        AddressInfoEntity result = addressInfoEntityMapper.queryByUserId(userId);
        return ServerResponse.createBySuccess(result);
    }

    @ApiOperation(value = "queryByJoin")
    @RequestMapping(value = "queryByJoin", method = RequestMethod.POST)
    public ServerResponse queryByJoin(@RequestParam long userId) {
        List result = addressInfoEntityMapper.queryByJoin(userId);
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
            String sql = "insert into address_info(user_id,address_name) VALUES (?,?)";
            //预编译sql
            pstm = conn.prepareStatement(sql);
            //开始总计时
            long bTime1 = System.currentTimeMillis();

            int index = 1;

            //循环10次，每次十万数据，一共2000万
            for (int i = 0; i < 200; i++) {

                //开启分段计时，计1W数据耗时
                long bTime = System.currentTimeMillis();
                //开始循环
                for (int j = 0; j < 100000; j++) {
                    //赋值
                    pstm.setLong(1, Integer.toUnsignedLong(index));
                    pstm.setString(2, "汕头");
                    //添加到同一个批处理中
                    pstm.addBatch();
                    index++;
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
            System.out.println("插入2000W数据共耗时：" + (eTime1 - bTime1));
            return ServerResponse.createBySuccess("插入2000W数据共耗时: " + (eTime1 - bTime1));

        } catch (Exception e) {
            e.printStackTrace();
            return ServerResponse.createByError();
        }
    }
}
