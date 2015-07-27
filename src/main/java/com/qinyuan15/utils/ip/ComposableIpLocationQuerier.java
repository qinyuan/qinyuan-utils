package com.qinyuan15.utils.ip;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.List;

public class ComposableIpLocationQuerier implements IpLocationQuerier {
    private final List<IpLocationQuerier> queriers;

    public ComposableIpLocationQuerier(List<IpLocationQuerier> queriers) {
        this.queriers = queriers;
    }

    public ComposableIpLocationQuerier(IpLocationQuerier querier, IpLocationQuerier... queriers) {
        List<IpLocationQuerier> list = Lists.newArrayList(querier);
        list.addAll(Lists.newArrayList(queriers));
        this.queriers = list;
    }

    @Override
    public String getLocation(String ip) {
        for (IpLocationQuerier querier : this.queriers) {
            if (querier == null) {
                continue;
            }
            String location = querier.getLocation(ip);
            if (StringUtils.hasText(location)) {
                return location;
            }
        }
        return null;
    }
}
