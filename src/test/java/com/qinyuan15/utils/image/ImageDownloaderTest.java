package com.qinyuan15.utils.image;

import com.qinyuan15.utils.test.TestFileUtils;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test ImageDownloader
 * Created by qinyuan on 15-1-14.
 */
public class ImageDownloaderTest {
    private ImageDownloader dowloader;

    @Before
    public void setUp() throws Exception {
        this.dowloader = mockImageDownloader();
    }

    @Test
    public void testDownload() throws Exception {
        String url = "http://www.baidu.com/img/bdlogo.png";
        String savePath = this.dowloader.save(url);
        System.out.println(savePath);
        assertThat(new File(savePath)).isFile();
    }

    @Test
    public void testGetSavePath() throws Exception {
        String testPath = "http://www.baidu.com/hello/world/test.png?key=value";
        String result = (String) Whitebox.getMethod(this.dowloader.getClass(), "getSavePath", String.class)
                .invoke(this.dowloader, testPath);
        assertThat(result).isEqualTo(TestFileUtils.tempDir + "/www.baidu.com/hello/world/test.png");
    }

    public static ImageDownloader mockImageDownloader() {
        return new ImageDownloader(TestFileUtils.tempDir);
    }
}
