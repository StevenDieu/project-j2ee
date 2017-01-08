package com.rizomm.ipii.steven.Controller;

import com.rizomm.ipii.steven.dao.ICategoryDao;
import com.rizomm.ipii.steven.dao.IProductDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Category;
import com.rizomm.ipii.steven.model.Product;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class ProductController implements Serializable {

    @EJB
    private ICategoryDao CD;

    @EJB
    private IProductDao PD;

    private Product product = new Product();
    private static final long serialVersionUID = 1L;

    public List<Category> findAllCategory() {
        return CD.findAllCategory();
    }

    public double getCountAllProduct() {
        return Math.ceil((double) PD.countAllProduct() / 9);
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

    public String getUrlBase() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURL().toString();
        return url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + "/";
    }


}