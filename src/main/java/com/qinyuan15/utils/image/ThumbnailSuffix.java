package com.qinyuan15.utils.image;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Class about Suffix of Thumbnail
 * Created by qinyuan on 15-3-18.
 */
public class ThumbnailSuffix {
    private ThumbnailSuffix() {
    }

    private static List<String> suffixes = Lists.newArrayList(
            "_thumbnail_small",
            "_thumbnail_middle"
    );

    public final static String SMALL_SUFFIX = suffixes.get(0);
    public final static String MIDDLE_SUFFIX = suffixes.get(1);

    public static List<String> getSuffixes() {
        return new ArrayList<>(suffixes);
    }
}
