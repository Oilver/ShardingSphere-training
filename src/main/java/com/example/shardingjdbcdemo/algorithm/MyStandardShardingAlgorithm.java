package com.example.shardingjdbcdemo.algorithm;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class MyStandardShardingAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {

        if (preciseShardingValue.getValue() >= 0 && preciseShardingValue.getValue() < 5000000) {
            return  "ds0";
        } else if (preciseShardingValue.getValue() >= 5000000 && preciseShardingValue.getValue() < 10000000) {
            return  "ds1";
        } else if (preciseShardingValue.getValue() >= 10000000 && preciseShardingValue.getValue() < 15000000) {
            return  "ds2";
        } else if (preciseShardingValue.getValue() >= 15000000) {
            return  "ds3";
        }
        return null;
    }
}
