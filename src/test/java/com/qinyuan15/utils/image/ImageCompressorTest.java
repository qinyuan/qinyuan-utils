package com.qinyuan15.utils.image;

import com.qinyuan15.utils.test.TestFileUtils;
import org.junit.Test;

/**
 * Test ImageCompressor
 * Created by qinyuan on 15-3-9.
 */
public class ImageCompressorTest {
    @Test
    public void testCompress() {
        ImageCompressor compressor = new ImageCompressor(TestFileUtils.getAbsolutePath("meituan.png"));

        String targetPath = TestFileUtils.tempDir + "/meituan2.png";
        compressor.compress(targetPath, 0.5);

        targetPath = TestFileUtils.tempDir + "/meituan3.png";
        compressor.compress(targetPath, 300, 400);
    }

    @Test
    public void testCompress2() {
        ImageCompressor compressor = new ImageCompressor(TestFileUtils.getAbsolutePath("png.jpg"));
        String targetPath = TestFileUtils.tempDir + "/png.jpg";
        compressor.compress(targetPath, 0.5);
    }
}
