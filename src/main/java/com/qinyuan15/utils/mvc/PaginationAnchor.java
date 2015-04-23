package com.qinyuan15.utils.mvc;

import java.lang.Math;import java.lang.String;import java.util.ArrayList;
import java.util.List;

/**
 * Bootstrap style anchor of pagination
 * Created by qinyuan on 15-4-3.
 */
public class PaginationAnchor {
    private String href;
    private String text;
    private String title;

    private PaginationAnchor(String href, String text, String title) {
        this.href = href;
        this.text = text;
        this.title = title;
    }

    private PaginationAnchor(String href, int i, String title) {
        this(href, String.valueOf(i), title);
    }

    public String getHref() {
        return href;
    }

    public String getText() {
        return text;
    }

    public String getTitle() {
        return title;
    }

    private final static String TO_FIRST_TEXT = "&laquo;";
    private final static String TO_LAST_TEXT = "&raquo;";

    public static List<PaginationAnchor> create(String pageUrl, int pageCount, int visibleAnchorCount,
                                                int currentPageNumber) {

        if (pageUrl.contains("?")) {
            if (!pageUrl.endsWith("&")) {
                pageUrl += "&";
            }
        } else {
            pageUrl += "?";
        }

        List<PaginationAnchor> anchors = new ArrayList<>();

        if (currentPageNumber == 1) {
            anchors.add(new PaginationAnchor(null, TO_FIRST_TEXT, null));
        } else {
            anchors.add(new PaginationAnchor(pageUrl + "pageNumber=1", TO_FIRST_TEXT, "第一页"));
        }

        int startPageNumber, endPageNumber;
        if (currentPageNumber <= (visibleAnchorCount + 1) / 2) {
            startPageNumber = 1;
            endPageNumber = Math.min(visibleAnchorCount, pageCount);
        } else if ((pageCount - currentPageNumber) <= visibleAnchorCount / 2) {
            endPageNumber = pageCount;
            startPageNumber = Math.max(1, pageCount - visibleAnchorCount + 1);
        } else {
            startPageNumber = currentPageNumber - (visibleAnchorCount + 1) / 2 + 1;
            endPageNumber = startPageNumber + visibleAnchorCount - 1;
        }

        for (int i = startPageNumber; i <= endPageNumber; i++) {
            if (i == currentPageNumber) {
                anchors.add(new PaginationAnchor(null, i, null));
            } else {
                anchors.add(new PaginationAnchor(pageUrl + "pageNumber=" + i, i, "第" + i + "页"));
            }
        }


        if (currentPageNumber == pageCount) {
            anchors.add(new PaginationAnchor(null, TO_LAST_TEXT, null));
        } else {
            anchors.add(new PaginationAnchor(pageUrl + "pageNumber=" + pageCount, TO_LAST_TEXT, "最后一页"));
        }
        return anchors;
    }
}
