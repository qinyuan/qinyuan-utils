package com.qinyuan15.utils.image;

import org.junit.Test;

/**
 * Test Thumbnail
 * Created by qinyuan on 15-3-10.
 */
public class ThumbnailTest {
    private ThumbnailBuilder thumbnail = new ThumbnailBuilder();

    @Test
    public void testGetSmall() throws Exception {
        String path = "/home/qinyuan/share/meituan.png";
        System.out.println(thumbnail.getSmall(path));
    }

    @Test
    public void testGetMiddle() throws Exception {
        String path = "/home/qinyuan/share/meituan.png";
        System.out.println(thumbnail.getMiddle(path));
    }
}
