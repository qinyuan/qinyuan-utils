package com.qinyuan15.utils.image;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to build Thumbnails
 * Created by qinyuan on 15-3-20.
 */
public class ThumbnailsBuilder {

    private ImageFilter imageFilter  = new ImageFilter().setFilterSize(null);
    private ThumbnailBuilder singleBuilder = new ThumbnailBuilder();

    public void setFilterSize(ImageSize filterSize) {
        this.imageFilter.setFilterSize(filterSize);
    }

    public List<String> getSmall(List<String> paths) {
        List<String> newPaths = new ArrayList<>();
        for (String path : this.imageFilter.filterSize(paths)) {
            newPaths.add(singleBuilder.getSmall(path));
        }
        return this.imageFilter.filterNull(newPaths);
    }

    public List<String> getMiddle(List<String> paths) {
        List<String> newPaths = new ArrayList<>();
        for (String path : this.imageFilter.filterSize(paths)) {
            newPaths.add(singleBuilder.getMiddle(path));
        }
        return this.imageFilter.filterNull(newPaths);
    }
}
