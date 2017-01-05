package com.rizomm.ipii.steven.dao;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.rizomm.ipii.steven.helper.Utils.*;
import static com.rizomm.ipii.steven.model.Order.*;

/**
 * Created by steven on 17/11/2016.
 */
@Stateless
public class OrderDao implements IOrderDao {

    @PersistenceContext(unitName = "projectJ2ee")
    protected EntityManager em;
    protected boolean isNotTest = true;

}
