package com.qinyuan15.utils.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;import java.lang.String;

/**
 * Parse HTML
 * Created by qinyuan on 14-12-24.
 */
public class HtmlParser {

    public final static String CLASS_ATTR_KEY = "class";
    private Document doc;

    public HtmlParser(String html) {
        doc = Jsoup.parse(html);
    }

    public String getTitle() {
        return getInnerHTML("title");
    }

    public Element getElement(String id) {
        return doc.getElementById(id);
    }

    public Elements getElements(String tagName) {
        return doc.getElementsByTag(tagName);
    }

    public Element getElement(String tagName, String className) {
        Elements elements = this.getElements(tagName, className);
        return elements.size() > 0 ? elements.get(0) : null;
    }

    public Elements getElements(String tagName, String className) {
        Elements elements = getElements(tagName);
        return filterElements(elements, className);
    }

    private static Elements filterElements(Elements elements, String className) {
        Elements filteredElements = new Elements();

        for (Element element : elements) {
            if (!element.hasAttr(CLASS_ATTR_KEY)) {
                continue;
            }

            String[] classAttrValues = element.attr(CLASS_ATTR_KEY).split("\\s+");
            for (String classAttrValue : classAttrValues) {
                if (classAttrValue.equals(className)) {
                    filteredElements.add(element);
                    break;
                }
            }
        }

        return filteredElements;
    }

    public static Elements getSubElements(Element element, String tagName, String className) {
        return filterElements(element.getElementsByTag(tagName), className);
    }

    public static Element getSubElement(Element element, String tagName, String className) {
        Elements elements = getSubElements(element, tagName, className);
        return elements.size() > 0 ? elements.get(0) : null;
    }

    public String getInnerHTML(String tagName) {
        Elements elements = doc.getElementsByTag(tagName);
        if (elements.size() > 0) {
            return elements.get(0).html();
        } else {
            return null;
        }
    }
}
