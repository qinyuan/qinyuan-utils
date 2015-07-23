package com.qinyuan15.utils.mvc.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Implement some methods of interface Table
 * Created by qinyuan on 15-7-23.
 */
public abstract class AbstractTable implements Table {
    private List<String> aliases = new ArrayList<>();
    private List<String> heads = new ArrayList<>();

    protected void addHeadAlias(String head, String alias) {
        this.heads.add(head);
        this.aliases.add(alias);
    }

    @Override
    public List<String> getAliases() {
        return aliases;
    }

    @Override
    public List<String> getHeads() {
        return heads;
    }
}
