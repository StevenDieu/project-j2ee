package com.rizomm.ipii.steven.Controller;

import com.rizomm.ipii.steven.dao.ICategoryDao;
import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Category;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class ProductController implements Serializable {

    @EJB
    private ICategoryDao CD;

    @EJB
    private IProductDao PD;

    private static final long serialVersionUID = 1L;

    public List<Category> findAllCategory(){
        return CD.findAllCategory();
    }

    public double getCountAllProduct() {
        int countProduct = PD.countAllProduct();
        return Math.ceil((double) countProduct / 9) ;
    }
}