package com.rizomm.ipii.steven.Controller;

import com.rizomm.ipii.steven.dao.ICategoryDao;
import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Category;
import com.rizomm.ipii.steven.model.Product;
import com.rizomm.ipii.steven.service.IShoppingCartService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.Path;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class CartController implements Serializable {

    @EJB
    private IShoppingCartService SCS;

    private static final long serialVersionUID = 1L;

}