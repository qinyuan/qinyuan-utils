package com.qinyuan15.utils.image;

import com.qinyuan15.utils.file.FileType;
import com.qinyuan15.utils.file.FileTypeJudge;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;import java.lang.Exception;import java.lang.RuntimeException;import java.lang.String;

/**
 * Class to parse image
 * Created by qinyuan on 15-3-10.
 */
public class ImageParser {
    private BufferedImage image;

    public ImageParser(BufferedImage image) {
        this.image = image;
    }

    public ImageParser(String sourcePath) {
        this(createBufferedImage(sourcePath));
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public static BufferedImage createBufferedImage(String sourcePath) {
        try {
            if (isJPG(sourcePath)) {
                JPEGImageDecoder decoder = JPEGCodec.createJPEGDecoder(
                        new FileInputStream(sourcePath));
                return decoder.decodeAsBufferedImage();
            } else {
                return ImageIO.read(new File(sourcePath));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isJPG(String sourcePath) {
        return new FileTypeJudge().getType(sourcePath).equals(FileType.JPEG);
    }
}
