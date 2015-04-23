package com.qinyuan15.utils.mvc;

import java.lang.Math; /**
 * Tool class about pagination
 * Created by qinyuan on 15-4-5.
 */
public class PaginationUtils {
    private PaginationUtils() {
    }

    public static int getPageCount(long itemCount, int pageSize) {
        return itemCount == 0 ? 1 : (int) (Math.ceil((double) itemCount / pageSize));
    }
}
