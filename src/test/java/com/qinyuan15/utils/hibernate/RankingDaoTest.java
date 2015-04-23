package com.qinyuan15.utils.hibernate;

import org.junit.Test;

/**
 * Test RankingDao
 * Created by qinyuan on 15-3-21.
 */
public class RankingDaoTest {
    private RankingDao dao = new RankingDao();

    @Test
    public void testGetMaxRanking() throws Exception {
        /*Integer maxRanking = dao.getMaxRanking(IndexLogo.class);
        System.out.println(maxRanking);*/
    }

    @Test
    public void testGetPrevious() {
        /*IndexLogoDao indexLogoDao = new IndexLogoDao();
        IndexLogo previous = dao.getPrevious(indexLogoDao.getInstance(10));
        System.out.println(previous == null);
        if (previous != null) {
            System.out.println(previous.getId());
        }

        System.out.println(dao.getPrevious(indexLogoDao.getInstance(8)) == null);*/
    }
}
