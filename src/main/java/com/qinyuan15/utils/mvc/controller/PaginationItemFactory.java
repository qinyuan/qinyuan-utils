package com.qinyuan15.utils.mvc.controller;

import java.util.List;

/**
 * Factory to provide pagination items
 * Created by qinyuan on 15-4-5.
 */
public interface PaginationItemFactory<T> {
    long getCount();

    List<T> getInstances(int firstResult, int maxResults);
}
