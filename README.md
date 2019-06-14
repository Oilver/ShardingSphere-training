# sharding-jdbc-by-yaml
使用分库分表轻量级框架sharding-jdbc搭配mysql写了个分库分表的demo
拉取代码刷新maven仓库可用。前提是配正确数据源、数据库信息，提前建立分库分表。
代码将持续更新，后续会加入读写分离（一主多从），zookeeper统一管理配置文件，强制路由，监控等功能。
目前采用自己的算法MyStandardShardingAlgorithm.java实现分库，分表算法为 (userId % 10) 
