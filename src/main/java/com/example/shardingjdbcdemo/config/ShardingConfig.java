package com.example.shardingjdbcdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.shardingjdbcdemo.algorithm.MyStandardShardingAlgorithm;
import io.shardingsphere.api.config.rule.ShardingRuleConfiguration;
import io.shardingsphere.api.config.rule.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.InlineShardingStrategyConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.orchestration.config.OrchestrationConfiguration;
import io.shardingsphere.orchestration.reg.api.RegistryCenterConfiguration;
import io.shardingsphere.shardingjdbc.orchestration.api.OrchestrationShardingDataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
public class ShardingConfig {

    // 适用Zookeeper
    private static final String ZOOKEEPER_CONNECTION_STRING = "localhost:2181";

    private static final String NAMESPACE = "orchestration-java-demo";

    @Autowired
    private DatasourceConfig datasourceConfig;

    /**
     * 这适用于将注册中心上的配置拉取下去覆盖自己的配置（配置中心化功能）
     * !!!自己写的算法，如分库算法、分表算法无法中心化
     * @return
     * @throws SQLException
     */
//    @Bean
//    public DataSource getDataSource() throws SQLException {
//        return OrchestrationShardingDataSourceFactory.createDataSource(new OrchestrationConfiguration("sharding-sphere-orchestration", getRegistryCenterConfiguration(), false));
//    }

    /**
     * 这适用于将local改变的配置覆盖注册中心上的配置
     *
     * @return
     * @throws SQLException
     */
    @Bean
    public DataSource dateSource() throws SQLException {
        Properties p = new Properties();
        p.setProperty("sql.show", Boolean.TRUE.toString());
        OrchestrationConfiguration orchestrationConfiguration = new OrchestrationConfiguration(
                "sharding-sphere-orchestration", getRegistryCenterConfiguration(), true);

        // 获取数据源对象
        return OrchestrationShardingDataSourceFactory.createDataSource(
                createDataSourceMap(), createShardingRuleConfig(), new HashMap<String, Object>(), p, orchestrationConfiguration
        );
    }

    private Map<String, DataSource> createDataSourceMap() {
        Map<String, DataSource> dataSourceMap = new HashMap<>();
        dataSourceMap.put("ds0", createDb0());
        dataSourceMap.put("ds1", createDb1());
        dataSourceMap.put("ds2", createDb2());
        dataSourceMap.put("ds3", createDb3());
        return dataSourceMap;
    }

    private ShardingRuleConfiguration createShardingRuleConfig() {
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        shardingRuleConfig.getTableRuleConfigs().add(addressRuleConfig());
        shardingRuleConfig.getTableRuleConfigs().add(orderRuleConfig());
        shardingRuleConfig.getBindingTableGroups().add("address_info_, order_info_");
        //默认数据源和分库策略
        shardingRuleConfig.setDefaultDataSourceName("ds0");
        shardingRuleConfig.setDefaultDatabaseShardingStrategyConfig(
                new StandardShardingStrategyConfiguration("user_id", new MyStandardShardingAlgorithm())
        );
        return shardingRuleConfig;
    }

    private RegistryCenterConfiguration getRegistryCenterConfiguration() {
        RegistryCenterConfiguration regConfig = new RegistryCenterConfiguration();
        regConfig.setServerLists(ZOOKEEPER_CONNECTION_STRING);
        regConfig.setNamespace(NAMESPACE);
        return regConfig;
    }

    //address表分片策略
    private TableRuleConfiguration addressRuleConfig() {
        // 配置address表规则
        TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration();
        tableRuleConfig.setLogicTable("address_info_");
        tableRuleConfig.setActualDataNodes("ds${0..1}.address_info_${0..9}");
        tableRuleConfig.setKeyGeneratorColumnName("address_id");
        //分表策略
        //自定义规则，规则比较麻烦，因此只能使用代码控制
        tableRuleConfig.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "address_info_${user_id % 10}"));
        return tableRuleConfig;
    }

    //order表分片策略
    private TableRuleConfiguration orderRuleConfig() {
        // 配置order表规则
        TableRuleConfiguration tableRuleConfig = new TableRuleConfiguration();
        tableRuleConfig.setLogicTable("order_info_");
        tableRuleConfig.setActualDataNodes("ds${0..1}.order_info_${0..9}");
        tableRuleConfig.setKeyGeneratorColumnName("order_id");
        //分表策略
        //自定义规则，规则比较麻烦，因此只能使用代码控制
        tableRuleConfig.setTableShardingStrategyConfig(
                new InlineShardingStrategyConfiguration("user_id", "order_info_${user_id % 10}"));
        return tableRuleConfig;
    }

    public DruidDataSource createDb0() {
        // 配置第一个数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(datasourceConfig.getClassName1());
        dataSource.setUrl(datasourceConfig.getUrl1());
        dataSource.setUsername(datasourceConfig.getUser1());
        dataSource.setPassword(datasourceConfig.getPassword1());
        // 每个分区最大的连接数
        dataSource.setMaxActive(20);
        // 每个分区最小的连接数
        dataSource.setMinIdle(5);
        return dataSource;
    }

    public DruidDataSource createDb1() {
        // 配置第一个数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(datasourceConfig.getClassName2());
        dataSource.setUrl(datasourceConfig.getUrl2());
        dataSource.setUsername(datasourceConfig.getUser2());
        dataSource.setPassword(datasourceConfig.getPassword2());
        // 每个分区最大的连接数
        dataSource.setMaxActive(20);
        // 每个分区最小的连接数
        dataSource.setMinIdle(5);
        return dataSource;
    }

    public DruidDataSource createDb2() {
        // 配置第一个数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(datasourceConfig.getClassName3());
        dataSource.setUrl(datasourceConfig.getUrl3());
        dataSource.setUsername(datasourceConfig.getUser3());
        dataSource.setPassword(datasourceConfig.getPassword3());
        // 每个分区最大的连接数
        dataSource.setMaxActive(20);
        // 每个分区最小的连接数
        dataSource.setMinIdle(5);
        return dataSource;
    }

    public DruidDataSource createDb3() {
        // 配置第一个数据源
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(datasourceConfig.getClassName4());
        dataSource.setUrl(datasourceConfig.getUrl4());
        dataSource.setUsername(datasourceConfig.getUser4());
        dataSource.setPassword(datasourceConfig.getPassword4());
        // 每个分区最大的连接数
        dataSource.setMaxActive(20);
        // 每个分区最小的连接数
        dataSource.setMinIdle(5);
        return dataSource;
    }
}
