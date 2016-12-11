package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.AbstractPersistentTest;
import org.junit.Before;

/**
 * Created by steven on 17/11/2016.
 */
public class OrderDaoTest extends AbstractPersistentTest {

    private OrderDao od = null;

    @Before
    public void init() {
        od = new OrderDao();
        od.em = em;
    }

}
