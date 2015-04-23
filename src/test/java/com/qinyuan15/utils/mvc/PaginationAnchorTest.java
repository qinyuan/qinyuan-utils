package com.qinyuan15.utils.mvc;

import junit.framework.TestCase;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test PaginationAnchor
 * Created by qinyuan on 15-4-3.
 */
public class PaginationAnchorTest extends TestCase {
    public void testCreate() throws Exception {
        String pageUrl = "helloWorld.jsp";
        List<PaginationAnchor> anchors = PaginationAnchor.create(pageUrl, 10, 5, 1);
        assertThat(anchors).hasSize(7);
        assertThat(anchors.get(0).getHref()).isNull();
        assertThat(anchors.get(1).getText()).isEqualTo("1");
        assertThat(anchors.get(5).getText()).isEqualTo("5");
        assertThat(anchors.get(6).getHref()).isNotNull();

        anchors = PaginationAnchor.create(pageUrl, 10, 5, 3);
        assertThat(anchors).hasSize(7);
        assertThat(anchors.get(0).getHref()).isNotNull();
        assertThat(anchors.get(1).getText()).isEqualTo("1");
        assertThat(anchors.get(5).getText()).isEqualTo("5");
        assertThat(anchors.get(6).getHref()).isNotNull();

        anchors = PaginationAnchor.create(pageUrl, 10, 5, 4);
        assertThat(anchors).hasSize(7);
        assertThat(anchors.get(1).getText()).isEqualTo("2");
        assertThat(anchors.get(5).getText()).isEqualTo("6");

        anchors = PaginationAnchor.create(pageUrl, 10, 5, 8);
        assertThat(anchors).hasSize(7);
        assertThat(anchors.get(1).getText()).isEqualTo("6");
        assertThat(anchors.get(5).getText()).isEqualTo("10");

        anchors = PaginationAnchor.create(pageUrl, 10, 5, 9);
        assertThat(anchors).hasSize(7);
        assertThat(anchors.get(1).getText()).isEqualTo("6");
        assertThat(anchors.get(5).getText()).isEqualTo("10");
        assertThat(anchors.get(4).getHref()).isNull();
        assertThat(anchors.get(6).getHref()).isNotNull();

        anchors = PaginationAnchor.create(pageUrl, 10, 5, 10);
        assertThat(anchors).hasSize(7);
        assertThat(anchors.get(1).getText()).isEqualTo("6");
        assertThat(anchors.get(5).getText()).isEqualTo("10");
        assertThat(anchors.get(6).getHref()).isNull();

        anchors = PaginationAnchor.create(pageUrl, 10, 4, 1);
        assertThat(anchors).hasSize(6);
        assertThat(anchors.get(1).getText()).isEqualTo("1");
        assertThat(anchors.get(4).getText()).isEqualTo("4");

        anchors = PaginationAnchor.create(pageUrl, 10, 4, 2);
        assertThat(anchors).hasSize(6);
        assertThat(anchors.get(1).getText()).isEqualTo("1");
        assertThat(anchors.get(4).getText()).isEqualTo("4");

        anchors = PaginationAnchor.create(pageUrl, 10, 4, 3);
        assertThat(anchors).hasSize(6);
        assertThat(anchors.get(1).getText()).isEqualTo("2");
        assertThat(anchors.get(4).getText()).isEqualTo("5");

        anchors = PaginationAnchor.create(pageUrl, 10, 4, 8);
        assertThat(anchors).hasSize(6);
        assertThat(anchors.get(1).getText()).isEqualTo("7");
        assertThat(anchors.get(4).getText()).isEqualTo("10");

        anchors = PaginationAnchor.create(pageUrl, 10, 4, 9);
        assertThat(anchors).hasSize(6);
        assertThat(anchors.get(1).getText()).isEqualTo("7");
        assertThat(anchors.get(4).getText()).isEqualTo("10");

        anchors = PaginationAnchor.create(pageUrl, 10, 8, 1);
        assertThat(anchors).hasSize(10);
        assertThat(anchors.get(1).getText()).isEqualTo("1");
        assertThat(anchors.get(8).getText()).isEqualTo("8");

        anchors = PaginationAnchor.create(pageUrl, 10, 8, 4);
        assertThat(anchors).hasSize(10);
        assertThat(anchors.get(1).getText()).isEqualTo("1");
        assertThat(anchors.get(8).getText()).isEqualTo("8");

        anchors = PaginationAnchor.create(pageUrl, 10, 8, 5);
        assertThat(anchors).hasSize(10);
        assertThat(anchors.get(1).getText()).isEqualTo("2");
        assertThat(anchors.get(8).getText()).isEqualTo("9");

        anchors = PaginationAnchor.create(pageUrl, 10, 8, 6);
        assertThat(anchors).hasSize(10);
        assertThat(anchors.get(1).getText()).isEqualTo("3");
        assertThat(anchors.get(8).getText()).isEqualTo("10");

        anchors = PaginationAnchor.create(pageUrl, 10, 7, 1);
        assertThat(anchors).hasSize(9);
        assertThat(anchors.get(1).getText()).isEqualTo("1");
        assertThat(anchors.get(7).getText()).isEqualTo("7");

        anchors = PaginationAnchor.create(pageUrl, 10, 7, 4);
        assertThat(anchors).hasSize(9);
        assertThat(anchors.get(1).getText()).isEqualTo("1");
        assertThat(anchors.get(7).getText()).isEqualTo("7");

        anchors = PaginationAnchor.create(pageUrl, 10, 7, 5);
        assertThat(anchors).hasSize(9);
        assertThat(anchors.get(1).getText()).isEqualTo("2");
        assertThat(anchors.get(7).getText()).isEqualTo("8");

        anchors = PaginationAnchor.create(pageUrl, 10, 7, 6);
        assertThat(anchors).hasSize(9);
        assertThat(anchors.get(1).getText()).isEqualTo("3");
        assertThat(anchors.get(7).getText()).isEqualTo("9");

        anchors = PaginationAnchor.create(pageUrl, 10, 7, 7);
        assertThat(anchors).hasSize(9);
        assertThat(anchors.get(1).getText()).isEqualTo("4");
        assertThat(anchors.get(7).getText()).isEqualTo("10");

        anchors = PaginationAnchor.create(pageUrl, 10, 7, 8);
        assertThat(anchors).hasSize(9);
        assertThat(anchors.get(1).getText()).isEqualTo("4");
        assertThat(anchors.get(7).getText()).isEqualTo("10");

        anchors = PaginationAnchor.create(pageUrl, 10, 11, 3);
        assertThat(anchors).hasSize(12);
        assertThat(anchors.get(1).getText()).isEqualTo("1");
        assertThat(anchors.get(10).getText()).isEqualTo("10");
    }
}
