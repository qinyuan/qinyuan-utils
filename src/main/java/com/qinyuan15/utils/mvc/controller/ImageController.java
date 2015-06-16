package com.qinyuan15.utils.mvc.controller;

import com.qinyuan15.utils.image.ImageDownloader;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * Controller to deal with image
 * Created by qinyuan on 15-6-16.
 */
public class ImageController extends BaseController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    protected ImageDownloader imageDownloader;

    /**
     * web frontend may post a image url or file, this method validate
     * the  url and upload file and return a local path
     *
     * @param imageUrl       image url posted
     * @param imageFile      image file uploaded
     * @param savePathPrefix save path prefix
     * @return save path
     * @throws java.io.IOException
     */
    protected String getSavePath(String imageUrl, MultipartFile imageFile, String savePathPrefix) throws IOException {
        if (isUploadFileNotEmpty(imageFile)) {
            /*
             * If upload file is not empty, move it to certain path
             */
            if (!savePathPrefix.endsWith("/")) {
                savePathPrefix = savePathPrefix + "/";
            }
            String relativePath = savePathPrefix + RandomStringUtils.randomAlphabetic(20)
                    + "_" + imageFile.getOriginalFilename();
            String filePath = imageDownloader.getSaveDir() + "/" + relativePath;
            File file = new File(filePath);
            File parent = file.getParentFile();
            if (!parent.isDirectory() && !parent.mkdirs()) {
                LOGGER.error("fail to create directory {}", parent.getAbsolutePath());
            }
            imageFile.transferTo(file);
            LOGGER.info("save upload image to {}", filePath);
            return filePath;
        } else {
            /*
             * If upload file is empty, deal with image url
             */
            if (isLocalUrl(imageUrl)) {
                return urlToPath(imageUrl);
            } else {
                String filePath = imageDownloader.save(imageUrl);
                LOGGER.info("save upload image to {}", filePath);
                return filePath;
            }
        }
    }

    private String urlToPath(String imageUrl) {
        String localAddress = getLocalAddress();
        int index = imageUrl.indexOf(localAddress);
        if (index >= 0) {
            return imageDownloader.getSaveDir() + "/" + imageUrl.substring(index + localAddress.length());
        } else {
            return null;
        }
    }

    private boolean isLocalUrl(String url) {
        return url.contains("://" + getLocalAddress());
    }

    protected String getSavePath(String imageUrl, MultipartFile imageFile) throws IOException {
        return getSavePath(imageUrl, imageFile, "");
    }

    protected boolean isUploadFileNotEmpty(MultipartFile file) {
        return file != null && StringUtils.hasText(file.getOriginalFilename())
                && file.getSize() > 0;
    }
}
