package com.nepxion.discovery.plugin.example.service.impl;

/**
 * <p>Title: Nepxion Discovery</p>
 * <p>Description: Nepxion Discovery</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.client.ServiceInstance;

import com.nepxion.discovery.common.constant.DiscoveryConstant;
import com.nepxion.discovery.plugin.framework.listener.discovery.AbstractDiscoveryListener;

// 当元数据中的group为mygroup，禁止被发现
public class MyDiscoveryListener extends AbstractDiscoveryListener {
    @Override
    public void onGetInstances(String serviceId, List<ServiceInstance> instances) {
        Iterator<ServiceInstance> iterator = instances.iterator();
        while (iterator.hasNext()) {
            ServiceInstance serviceInstance = iterator.next();
            String group = serviceInstance.getMetadata().get(DiscoveryConstant.GROUP);
            if (StringUtils.equals(group, "mygroup2")) {
                iterator.remove();

                System.out.println("********** 服务名=" + serviceId + "，组名=" + group + "的服务不允许被其它服务发现");
            }
        }
    }

    @Override
    public void onGetServices(List<String> services) {

    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE - 1;
    }
}