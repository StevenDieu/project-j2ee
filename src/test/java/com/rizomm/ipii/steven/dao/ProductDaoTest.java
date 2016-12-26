package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.AbstractPersistentTest;
import com.rizomm.ipii.steven.model.Category;
import com.rizomm.ipii.steven.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by steven on 17/11/2016.
 */
public class ProductDaoTest extends AbstractPersistentTest {

    private ProductDao pd = null;
    private CategoryDao cd = null;

    @Before
    public void init() {
        pd = new ProductDao();
        pd.em = em;
        pd.isNotTest = false;

        cd = new CategoryDao();
        cd.em = em;
        cd.isNotTest = false;
    }

    @Test
    public void shouldCreateACategoty() throws Exception {

        Category category = new Category(1, "test");
        Product product = new Product(1, category, 5, 1.F, "Name of product", "/img/picture");
        assertNull("Product should not be found", em.find(Product.class, product.getId()));

        pd.createProduct(product);
        assertNotNull("Product should not be found", em.find(Product.class, product.getId()));
    }


    @Test
    public void shouldCreateACategotyEmpty() throws Exception {
        Product product = new Product();
        assertNull("Product should not be found", em.find(Product.class, product.getId()));

        Product isCreate = pd.createProduct(product);
        assertNull("Product should be created with product empty", isCreate);
    }

    @Test
    public void shouldFindACategoty() throws Exception {
        Category category = new Category(1, "test");
        Product product = new Product(1, category, 5, 1.F, "Name of product", "/img/picture");
        assertNull("Product should not be found", em.find(Product.class, product.getId()));

        pd.createProduct(product);
        assertNotNull("Product should not be found", em.find(Product.class, product.getId()));

        Product findCategorie = pd.findProductById(product.getId());
        assertEquals("Product should be find with product", findCategorie.getId(), 1);
    }

    @Test
    public void shouldFindACategotyById() throws Exception {
        Category category = new Category(1, "test");
        Product product = new Product(1, category, 5, 1.F, "Name of product", "/img/picture");
        assertNull("Product should not be found", em.find(Product.class, product.getId()));

        pd.createProduct(product);
        assertNotNull("Product should not be found", em.find(Product.class, product.getId()));

        Product findCategorie = pd.findProductById(product.getId());
        assertEquals("Product should be find with product", findCategorie.getId(), 1);
    }

    @Test
    public void shouldFindAllCategoty() throws Exception {
        Category category = new Category(1, "test");
        Product product = new Product(1, category, 5, 1.F, "Name of product", "/img/picture");
        Product product2 = new Product(2, category, 5, 1.F, "Name of product2", "/img/picture");
        assertNull("Product should not be found", em.find(Product.class, product.getId()));

        tx.begin();
        cd.createCategory(category);
        pd.createProduct(product);
        tx.commit();

        tx.begin();
        pd.createProduct(product2);
        tx.commit();

        assertNotNull("Product should not be found", em.find(Product.class, product.getId()));

        List<Product> allCategorie = pd.findAllProduct();
        assertEquals("Nummber to search product", allCategorie.size(), 2);

        deleteAllProduct();
    }

    @Test
    public void shouldDeleteProduct() throws Exception {
        Category category = new Category(1, "test");
        Product product = new Product(1, category, 5, 1.F, "Name of product", "/img/picture");
        Product product2 = new Product(2, category, 5, 1.F, "Name of product2", "/img/picture");
        assertNull("Product should not be found", em.find(Product.class, product.getId()));

        tx.begin();
        cd.createCategory(category);
        pd.createProduct(product);
        tx.commit();

        tx.begin();
        pd.createProduct(product2);
        tx.commit();

        tx.begin();
        Boolean isDeleted = pd.deleteProduct(product);
        assertTrue("Product should not be not deleted", isDeleted);
        tx.commit();

        List<Product> allCategorie = pd.findAllProduct();
        assertEquals("Nummber to search product", allCategorie.size(), 1);
        assertEquals("Product should be not a greate product", allCategorie.get(0).getId(), 2);

        deleteAllProduct();
    }

    @Test
    public void shouldDeleteProductById() throws Exception {
        Category category = new Category(1, "test");
        Product product = new Product(1, category, 5, 1.F, "Name of product", "/img/picture");
        Product product2 = new Product(2, category, 5, 1.F, "Name of product2", "/img/picture");
        assertNull("Product should not be found", em.find(Product.class, product.getId()));

        tx.begin();
        cd.createCategory(category);
        pd.createProduct(product);
        tx.commit();

        tx.begin();
        pd.createProduct(product2);
        tx.commit();

        tx.begin();
        Boolean isDeleted = pd.deleteProductById(product2.getId());
        assertTrue("Product should not be not deleted", isDeleted);
        tx.commit();

        List<Product> allCategorie = pd.findAllProduct();
        assertEquals("Nummber to search product", allCategorie.size(), 1);
        assertEquals("Product should be not a greate product", allCategorie.get(0).getId(), 1);

        deleteAllProduct();
    }


    @Test
    public void shouldUpdateProduct() throws Exception {
        Category category = new Category(1, "test");
        Product product = new Product(1, category, 5, 1.F, "Name of product", "/img/picture");
        Product productUpdate = new Product(1, category, 5, 1.F, "Name of product2", "/img/picture");
        assertNull("Product should not be found", em.find(Product.class, product.getId()));

        tx.begin();
        cd.createCategory(category);
        pd.createProduct(product);
        tx.commit();

        List<Product> allCategorie = pd.findAllProduct();
        assertEquals("Product is not correct", allCategorie.get(0).getName(), "Name of product");

        tx.begin();
        pd.updateProduct(productUpdate);
        tx.commit();

        allCategorie = pd.findAllProduct();
        assertEquals("Product is not correct", allCategorie.get(0).getName(), "Name of product2");

        deleteAllProduct();
    }

    private void deleteAllCategory() {
        tx.begin();
        cd.deleteAllCategory();
        tx.commit();
    }

    private void deleteAllProduct() {
        tx.begin();
        pd.deleteAllProduct();
        tx.commit();
        deleteAllCategory();
    }

}
