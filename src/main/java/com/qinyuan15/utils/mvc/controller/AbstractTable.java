package com.qinyuan15.utils.mvc.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement some methods of interface Table
 * Created by qinyuan on 15-7-23.
 */
public abstract class AbstractTable implements Table {
    private final List<String> aliases = new ArrayList<>();
    private final List<String> heads = new ArrayList<>();
    private final List<String> headStyles = new ArrayList<>();

    protected void addHeadAlias(String head, String alias) {
        if (aliases.contains(alias)) {
            throw new RuntimeException("There is more than one alias named '" + alias + "'");
        }

        this.heads.add(head);
        this.aliases.add(alias);
        this.headStyles.add("filter");
    }

    private void addHeadStyle(String alias, String style) {
        int fieldIndex = getAliases().indexOf(alias);
        if (fieldIndex >= 0 && fieldIndex < headStyles.size()) {
            headStyles.set(fieldIndex, headStyles.get(fieldIndex) + " " + style);
        }
    }

    protected void addOrderStyle(String alias, boolean asc) {
        addHeadStyle(alias, asc ? "asc" : "desc");
    }

    protected void addFilterStyle(String alias) {
        addHeadStyle(alias, "filtered");
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public List<String> getHeads() {
        return heads;
    }

    @Override
    public List<String> getHeadStyles() {
        return headStyles;
    }
}
