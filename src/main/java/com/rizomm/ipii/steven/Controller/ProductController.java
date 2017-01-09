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

/**
 * Class ProductController ...
 *
 * @author steven
 *         Created on 09/01/2017
 */
@Named
@RequestScoped
public class ProductController implements Serializable {

    @EJB
    private ICategoryDao CD;

    @EJB
    private IProductDao PD;

    private Product product = new Product();
    private static final long serialVersionUID = 1L;

    /**
     * Method findAllCategory find all category
     *
     * @return List<Category>
     */
    public List<Category> findAllCategory() {
        return CD.findAllCategory();
    }

    /**
     * Method getCountAllProduct returns the countAllProduct of this ProductController object.
     *
     * @return the countAllProduct (type double) of this ProductController object.
     */
    public double getCountAllProduct() {
        return Math.ceil((double) PD.countAllProduct() / 9);
    }

    /**
     * Method doFindProduct ...
     */
    public void doFindProduct() {
        product = PD.findProductById(product.getId());
    }

    /**
     * Method getProduct returns the product of this ProductController object.
     *
     * @return the product (type Product) of this ProductController object.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Method getPriceDixieme returns the priceDixieme of this ProductController object.
     *
     * @return the priceDixieme (type String) of this ProductController object.
     */
    public String getPriceDixieme() {
        return Utils.convertDoubleToStringWithDixieme(product.getPrice());
    }

    /**
     * Method getUrlBase returns the urlBase of this ProductController object.
     *
     * @return the urlBase (type String) of this ProductController object.
     */
    public String getUrlBase() {
        HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String url = req.getRequestURL().toString();
        return url.substring(0, url.length() - req.getRequestURI().length()) + req.getContextPath() + "/";
    }


}