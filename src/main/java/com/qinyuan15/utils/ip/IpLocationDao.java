package com.qinyuan15.utils.ip;

import com.qinyuan15.utils.hibernate.HibernateListBuilder;
import com.qinyuan15.utils.hibernate.HibernateUtils;

import java.util.List;

public class IpLocationDao {
    public List<IpLocation> getInstances() {
        return new HibernateListBuilder().build(IpLocation.class);
    }

    public IpLocation getInstanceByIp(String ip) {
        return new HibernateListBuilder().addEqualFilter("ip", ip).getFirstItem(IpLocation.class);
    }

    public boolean hasIp(String ip) {
        return new HibernateListBuilder().addEqualFilter("ip", ip).count(IpLocation.class) > 0;
    }

    public String getLocationByIp(String ip) {
        IpLocation ipLocation = getInstanceByIp(ip);
        return ipLocation == null ? null : ipLocation.getLocation();
    }

    public Integer save(String ip, String location) {
        IpLocation ipLocation = getInstanceByIp(ip);
        if (ipLocation == null) {
            ipLocation = new IpLocation();
            ipLocation.setIp(ip);
            ipLocation.setLocation(location);
            return HibernateUtils.save(ipLocation);
        } else {
            ipLocation.setLocation(location);
            HibernateUtils.update(ipLocation);
            return ipLocation.getId();
        }
    }
}
