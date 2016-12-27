package com.rizomm.ipii.steven.Controller;

import com.rizomm.ipii.steven.dao.ICategoryDao;
import com.rizomm.ipii.steven.model.Category;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class ProductController implements Serializable {

    @EJB
    private ICategoryDao CD;

    private static final long serialVersionUID = 1L;

    public List<Category> findAllCategory(){
        return CD.findAllCategory();
    }
}