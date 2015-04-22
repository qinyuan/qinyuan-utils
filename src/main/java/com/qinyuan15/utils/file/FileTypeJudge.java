package com.qinyuan15.utils.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class to judge file type
 * Created by qinyuan on 15-3-22.
 */
public class FileTypeJudge {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileTypeJudge.class);

    /**
     * Convert file header to hex string
     *
     * @param sourceBytes byte codes of file header
     * @return hex string
     */
    private String bytesToHexString(byte[] sourceBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        if (sourceBytes == null || sourceBytes.length <= 0) {
            return null;
        }
        for (byte b : sourceBytes) {
            int v = b & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * get head of file
     *
     * @param filePath path of file
     * @return head of file
     */
    private String getFileHead(String filePath) {
        try (InputStream inputStream = new FileInputStream(filePath)) {
            int byteCountToRead = 28;
            byte[] b = new byte[byteCountToRead];
            int readCount = inputStream.read(b, 0, byteCountToRead);
            if (readCount < byteCountToRead) {
                LOGGER.warn("Fail to read {} bytes from file {}, only read {} bytes",
                        byteCountToRead, filePath, readCount);
            }
            return bytesToHexString(b);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 判断文件类型
     *
     * @param filePath 文件路径
     * @return 文件类型
     */
    public FileType getType(String filePath) {
        String fileHead = getFileHead(filePath);
        if (!StringUtils.hasLength(fileHead)) {
            return null;
        }

        fileHead = fileHead.toUpperCase();
        for (FileType type : FileType.values()) {
            if (fileHead.startsWith(type.getValue())) {
                return type;
            }
        }

        return null;
    }
}
