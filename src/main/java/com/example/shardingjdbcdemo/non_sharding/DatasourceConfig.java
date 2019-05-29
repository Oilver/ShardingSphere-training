package com.example.shardingjdbcdemo.non_sharding;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource(value = {"classpath:datasource.properties"})
@Component
@Data
@Configuration
public class DatasourceConfig {

    @Value("${className}")
    private String className;

    @Value("${url}")
    private String url;

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    /**
     * 配置数据源
     *
     * @return
     */
//    @Bean(value = "test")
//    public DruidDataSource dataSource() {
//        // 配置第一个数据源
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(getClassName());
//        dataSource.setUrl(getUrl());
//        dataSource.setUsername(getUser());
//        dataSource.setPassword(getPassword());
//        dataSource.setValidationQueryTimeout(60000);
//        // 每个分区最大的连接数
//        dataSource.setMaxActive(20);
//        // 每个分区最小的连接数
//        dataSource.setMinIdle(5);
//        return dataSource;
//    }
}