package com.qinyuan15.utils.hibernate;

import java.util.List;

public abstract class AbstractRankingDao<T> extends AbstractDao<T> {
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getInstances() {
        return new RankingDao().getInstances(getPersistClass());
    }



    public void rankUp(int id) {
        new RankingDao().rankUp(getPersistClass(), id);
    }

    public void rankDown(int id) {
        new RankingDao().rankDown(getPersistClass(), id);
    }
}
