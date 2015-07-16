package com.qinyuan15.utils.sns.share;

import java.lang.String;import java.util.List;

/**
 * Class to build share url
 * Created by qinyuan on 15-7-16.
 */
public interface ShareUrlBuilder {
    String build(String target, String text, List<String> imageUrls);
}
