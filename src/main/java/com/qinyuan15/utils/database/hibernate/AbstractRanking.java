package com.qinyuan15.utils.database.hibernate;

/**
 * Default implement of Ranking interface
 * Created by qinyuan on 15-7-2.
 */
public abstract class AbstractRanking extends PersistObject implements Ranking {
    private Integer ranking;

    @Override
    public Integer getRanking() {
        return ranking;
    }

    @Override
    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }
}
