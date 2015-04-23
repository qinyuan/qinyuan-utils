package com.qinyuan15.utils.image;

import com.qinyuan15.utils.CommandUtils;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;import java.lang.RuntimeException;import java.lang.String;

/**
 * Class to compress image
 * Created by qinyuan on 15-3-9.
 */
public class ImageCompressor {

    private String sourcePath;
    private BufferedImage sourceImage;

    public ImageCompressor(String sourcePath) {
        this.sourcePath = sourcePath;
        this.sourceImage = ImageParser.createBufferedImage(this.sourcePath);
    }

    public int getWidth() {
        return this.sourceImage.getWidth();
    }

    public int getHeight() {
        return this.sourceImage.getHeight();
    }

    public void compress(String targetPath, double rate) {
        int width = (int) (getWidth() * rate);
        int height = (int) (getHeight() * rate);
        compress(targetPath, width, height);
    }

    public void compress(String targetPath, int width, int height) {
        if (width > getWidth()) {
            width = getWidth();
        }
        if (height > getHeight()) {
            height = getHeight();
        }

        if (CommandUtils.run("which convert").getKey().equals(0)) {
            compressByImageMagick(targetPath, width, height);
        } else {
            compressByScalr(targetPath, width, height);
        }
    }

    private void compressByImageMagick(String targetPath, int width, int height) {
        String cmd = "convert -resize " + width + "x" + height + " " + this.sourcePath + " " + targetPath;
        CommandUtils.run(cmd);
    }

    private void compressByScalr(String targetPath, int width, int height) {
        try {
            File targetFile = new File(targetPath);
            BufferedImage targetImage = Scalr.resize(sourceImage, Scalr.Method.QUALITY,
                    Scalr.Mode.AUTOMATIC, width, height, Scalr.OP_ANTIALIAS);
            ImageIO.write(targetImage, getFormatName(), targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void compress(String targetPath, ImageSize size) {
        this.compress(targetPath, size.width, size.height);
    }

    private String getFormatName() {
        sourcePath = sourcePath.toLowerCase();
        if (sourcePath.endsWith(".png")) {
            return "png";
        } else if (sourcePath.endsWith(".gif")) {
            return "gif";
        } else {
            return "jpeg";
        }
    }
}
