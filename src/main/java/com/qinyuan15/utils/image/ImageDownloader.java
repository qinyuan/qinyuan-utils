package com.qinyuan15.utils.image;

import com.qinyuan15.utils.http.HttpClient;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to download image from certain url
 * Created by qinyuan on 15-1-14.
 */
// TODO this class doesn't use proxy
public class ImageDownloader {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImageDownloader.class);

    private String saveDir;
    private boolean useRandomSaveName;

    public ImageDownloader(String saveDir) {
        this.saveDir = saveDir;
    }

    public String getSaveDir() {
        return this.saveDir;
    }

    public ImageDownloader setUseRandomSaveName(boolean useRandomSaveName) {
        this.useRandomSaveName = useRandomSaveName;
        return this;
    }

    /**
     * Save image of certain url as image then return the save path
     *
     * @param url url of image to save
     * @return save path of the image
     */
    public String save(String url) {
        if (!StringUtils.hasText(url)) {
            String info = "empty url: '" + url + "'";
            LOGGER.error(info);
            throw new RuntimeException(info);
        }

        String savePath = getSavePath(url);
        try {
            LOGGER.info("prepare to save image {} to {}", url, savePath);

            // create input stream and output stream
            HttpClient client = new HttpClient();
            CloseableHttpResponse response = client.getResponse(url);
            response.getEntity().getContent();
            BufferedInputStream in = new BufferedInputStream(response.getEntity().getContent());
            File saveFile = new File(savePath);
            File parentDir = saveFile.getParentFile();
            if (!parentDir.isDirectory() && !parentDir.mkdirs()) {
                throw new RuntimeException("fail to create directory " + parentDir.getAbsolutePath());
            }
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));

            // save file
            byte[] buf = new byte[2048];
            int length;
            while ((length = in.read(buf)) != -1) {
                out.write(buf, 0, length);
            }
            in.close();
            out.close();

            LOGGER.info("save image {} to {}", url, savePath);
            return savePath;
        } catch (Exception e) {
            LOGGER.error("fail to save image {} to path {}: {}", url, savePath, e);
            throw new RuntimeException(e);
        }
    }

    public List<String> save(List<String> urls) {
        List<String> savePaths = new ArrayList<>();
        for (String url : urls) {
            try {
                savePaths.add(this.save(url));
            } catch (Exception e) {
                LOGGER.error("fail to download {}: {}", url, e);
            }
        }
        return savePaths;
    }

    private String getNameFromUrl(String url) {
        int startIndex = url.lastIndexOf("/");
        if (startIndex < 0) {
            return url;
        } else if (startIndex == url.length() - 1) {
            return "";
        } else {
            return url.substring(startIndex + 1);
        }
    }


    private String getSavePath(String url) {
        if (this.useRandomSaveName) {
            while (true) {
                String fileName = getNameFromUrl(url);
                if (fileName.length() > 40) {
                    fileName = fileName.substring(fileName.length() - 40);
                }
                fileName = RandomStringUtils.randomAlphabetic(20) + "_" + fileName;
                String filePath = this.saveDir + "/" + fileName;
                if (!new File(filePath).exists()) {
                    return filePath;
                }
            }
        } else {
            url = url.replaceAll("^.*://", "").replaceAll("\\?.*", "");
            return this.saveDir + "/" + url;
        }
    }
}
