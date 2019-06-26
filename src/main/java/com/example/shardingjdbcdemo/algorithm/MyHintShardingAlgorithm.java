package com.example.shardingjdbcdemo.algorithm;

import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.ArrayList;
import java.util.Collection;

public class MyHintShardingAlgorithm implements HintShardingAlgorithm<Long> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<Long> hintShardingValue) {
        Collection<String> result = new ArrayList<>();
        for (String each : collection) {
            for (Long value : hintShardingValue.getValues()) {
                String temp = String.valueOf(value);
                if (each.endsWith(temp.substring(temp.length()-1))) {
                    result.add(each);
                }
            }
        }
        return result;
    }
}
