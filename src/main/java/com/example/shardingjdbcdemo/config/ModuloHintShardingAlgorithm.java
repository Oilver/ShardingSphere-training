
package com.example.shardingjdbcdemo.config;

import io.shardingsphere.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.api.algorithm.sharding.hint.HintShardingAlgorithm;

import java.util.ArrayList;
import java.util.Collection;

public final class ModuloHintShardingAlgorithm implements HintShardingAlgorithm {


    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ShardingValue shardingValue) {
//        Collection<String> result = new ArrayList<>();
//        for (String each : availableTargetNames) {
//            for (Long value : shardingValue.getValues()) {
//                if (each.endsWith(String.valueOf(value % 2))) {
//                    result.add(each);
//                }
//            }
//        }
        return null;
    }
}
