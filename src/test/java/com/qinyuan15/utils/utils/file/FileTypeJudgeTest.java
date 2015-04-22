package com.qinyuan15.utils.utils.file;

import com.qinyuan15.utils.file.FileType;
import com.qinyuan15.utils.file.FileTypeJudge;
import com.qinyuan15.utils.lib.TestFileUtils;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test FileTypeJudge
 * Created by qinyuan on 15-3-22.
 */
public class FileTypeJudgeTest {
    private FileTypeJudge fileTypeJudge = new FileTypeJudge();

    @Test
    public void testGetType() throws Exception {
        String testFile = TestFileUtils.getAbsolutePath("png.jpg");
        assertThat(fileTypeJudge.getType(testFile)).isEqualTo(FileType.PNG);

        testFile = TestFileUtils.getAbsolutePath("meituan.png");
        assertThat(fileTypeJudge.getType(testFile)).isEqualTo(FileType.PNG);

        testFile = TestFileUtils.getAbsolutePath("jpg.jpg");
        assertThat(fileTypeJudge.getType(testFile)).isEqualTo(FileType.JPEG);
    }
}
