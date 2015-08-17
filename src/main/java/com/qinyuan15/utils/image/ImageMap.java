package com.qinyuan15.utils.image;

import com.qinyuan15.utils.database.hibernate.PersistObject;

/**
 * Persist Class of table image_map
 * Created by qinyuan on 15-6-26.
 */
public class ImageMap extends PersistObject {
    private String relateType;
    private Integer relateId;
    private Integer xStart;
    private Integer yStart;
    private Integer xEnd;
    private Integer yEnd;
    private String href;
    private String comment;

    public String getRelateType() {
        return relateType;
    }

    public Integer getRelateId() {
        return relateId;
    }

    public Integer getxStart() {
        return xStart;
    }

    public Integer getyStart() {
        return yStart;
    }

    public Integer getxEnd() {
        return xEnd;
    }

    public Integer getyEnd() {
        return yEnd;
    }

    public String getHref() {
        return href;
    }

    public String getComment() {
        return comment;
    }

    public void setRelateType(String relateType) {
        this.relateType = relateType;
    }

    public void setRelateId(Integer relateId) {
        this.relateId = relateId;
    }

    public void setxStart(Integer xStart) {
        this.xStart = xStart;
    }

    public void setyStart(Integer yStart) {
        this.yStart = yStart;
    }

    public void setxEnd(Integer xEnd) {
        this.xEnd = xEnd;
    }

    public void setyEnd(Integer yEnd) {
        this.yEnd = yEnd;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
