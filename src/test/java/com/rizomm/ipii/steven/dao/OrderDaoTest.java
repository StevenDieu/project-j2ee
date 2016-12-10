package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.AbstractPersistentTest;
import com.rizomm.ipii.steven.model.Category;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by steven on 17/11/2016.
 */
public class OrderDaoTest extends AbstractPersistentTest {

    private OrderDao od = null;

    @Before
    public void init(){
        od = new OrderDao();
        od.em = em;
    }

}
