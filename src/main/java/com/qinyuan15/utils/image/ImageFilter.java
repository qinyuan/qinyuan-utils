package com.qinyuan15.utils.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.Exception;import java.lang.String;import java.util.ArrayList;
import java.util.List;

/**
 * Class to filter images
 * Created by qinyuan on 15-3-21.
 */
public class ImageFilter {
    private final static Logger LOGGER = LoggerFactory.getLogger(ImageFilter.class);
    private ImageSize filterSize;

    public ImageFilter setFilterSize(ImageSize filterSize) {
        this.filterSize = filterSize;
        return this;
    }

    private boolean matchFilterSize(String path) {
        if (this.filterSize == null) {
            return true;
        }

        try {
            ImageParser imageParser = new ImageParser(path);
            return imageParser.getHeight() > this.filterSize.height ||
                    imageParser.getWidth() > this.filterSize.width;
        } catch (Exception e) {
            LOGGER.error("Fail to get size of image {}: {}", path, e);
            return true;
        }
    }


    public List<String> filterSize(List<String> paths) {
        List<String> filteredPaths = new ArrayList<>();
        if (paths == null) {
            return filteredPaths;
        }

        for (String path : this.filterNull(paths)) {
            if (this.matchFilterSize(path)) {
                filteredPaths.add(path);
            }
        }
        return filteredPaths;
    }

    public List<String> filterNull(List<String> paths) {
        List<String> filteredPaths = new ArrayList<>();
        for (String path : paths) {
            if (path != null) {
                filteredPaths.add(path);
            }
        }
        return filteredPaths;
    }
}
