package com.qinyuan15.utils.mvc.controller;

import com.qinyuan15.utils.config.ImageConfig;

/**
 * Class to adapter image url
 * Created by qinyuan on 15-6-21.
 */
public class ImageUrlAdapter {

    private final ImageConfig imageConfig;
    private final String localAddress;

    public ImageUrlAdapter(ImageConfig imageConfig, String localAddress) {
        this.imageConfig = imageConfig;
        this.localAddress = localAddress;
    }

    public String pathToUrl(String path) {
        if (path == null) {
            return null;
        }

        path = com.qinyuan15.utils.StringUtils.replaceFirst(path, imageConfig.getDirectory(), "");
        while (path.startsWith("/")) {
            path = path.substring(1);
        }
        return getUrlPrefix() + path;
    }

    public String urlToPath(String imageUrl) {
        if (imageUrl == null) {
            return null;
        }

        String path = imageConfig.getDirectory();
        if (!path.endsWith("/")) {
            path += "/";
        }
        return path + imageUrl.substring(getUrlPrefix().length());
    }

    private String getUrlPrefix() {
        String content = imageConfig.getContext();
        if (!content.endsWith("/")) {
            content += "/";
        }
        return imageConfig.getProtocal() + "://" + localAddress + ":" + imageConfig.getPort() +
                "/" + content;
    }
}
