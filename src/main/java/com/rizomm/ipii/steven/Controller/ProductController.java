package com.rizomm.ipii.steven.Controller;

import com.rizomm.ipii.steven.dao.ICategoryDao;
import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Category;
import com.rizomm.ipii.steven.model.Product;
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
@Path("/home")
public class ProductController implements Serializable {

    @EJB
    private ICategoryDao CD;

    @EJB
    private IProductDao PD;

    private Product product = new Product();
    private String priceDixieme;

    private static final long serialVersionUID = 1L;

    @Path("/test")
    public List<Category> findAllCategory(){
        return CD.findAllCategory();
    }

    public double getCountAllProduct() {
        int countProduct = PD.countAllProduct();
        return Math.ceil((double) countProduct / 9) ;
    }

    public void doFindProduct() {
        product = PD.findProductById(product.getId());
    }

    public Product getProduct() {
        return product;
    }

    public String getPriceDixieme() {
        return Utils.convertDoubleToStringWithDixieme(product.getPrice());
    }
}