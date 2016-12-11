package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Product;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.rizomm.ipii.steven.model.Product.DELETE_ALL;
import static com.rizomm.ipii.steven.model.Product.FIND_ALL;

/**
 * Created by steven on 17/11/2016.
 */
@Stateless
@Local
public class ProductDao implements IProductDao {

    @PersistenceContext(unitName = "projectJ2ee")
    protected EntityManager em;
    protected boolean isNotTest = true;

    @Override
    public boolean createProduct(Product product) {
        if (Utils.isNotEmpty(product.getName())) {
            em.persist(product);
            return true;
        }
        return false;
    }

    @Override
    public Product findProductById(int idProduct) {
        Product findProduct = em.find(Product.class, idProduct);
        return findProduct;
    }

    @Override
    public List<Product> findAllProduct() {
        TypedQuery<Product> query = em.createNamedQuery(FIND_ALL, Product.class);
        if (isNotTest) {
            em.joinTransaction();
        }
        return query.getResultList();
    }

    @Override
    public void deleteAllProduct() {
        em.createNamedQuery(DELETE_ALL, Product.class).executeUpdate();
    }

    @Override
    public Boolean deleteProductById(int idProduct) {
        Product product = em.find(Product.class, idProduct);
        if (Utils.isNotEmpty(product)) {
            return deleteProduct(product);
        }
        return false;
    }

    @Override
    public Boolean deleteProduct(Product product) {
        em.remove(product);
        Product findProduct = em.find(Product.class, product.getId());
        if (Utils.isEmpty(findProduct)) {
            return true;
        }
        return false;
    }

    @Override
    public Product updateProduct(Product product) {
        return em.merge(product);
    }

}
