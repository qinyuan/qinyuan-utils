package com.qinyuan15.utils.ip;

import java.util.ArrayList;
import java.util.List;

public class DefaultIpLocationQuerier extends ComposableIpLocationQuerier {

    public DefaultIpLocationQuerier() {
        super(getQueries());
    }

    private static List<IpLocationQuerier> getQueries() {
        List<IpLocationQuerier> queriers = new ArrayList<>();
        queriers.add(new DatabaseIpLocationQuerier());

        IpLocationSaver ipLocationSaver = new DatabaseIpLocationSaver();

        NetworkIpLocationQuerier querier = new TaobaoIpLocationQuerier();
        querier.setIpLocationSaver(ipLocationSaver);
        queriers.add(querier);

        querier = new BaiduIpLocationQuerier();
        querier.setIpLocationSaver(ipLocationSaver);
        queriers.add(querier);

        querier = new SogouIpLocationQuerier();
        querier.setIpLocationSaver(ipLocationSaver);
        queriers.add(querier);

        return queriers;
    }
}
