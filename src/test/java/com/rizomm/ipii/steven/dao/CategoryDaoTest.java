package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.AbstractPersistentTest;
import com.rizomm.ipii.steven.model.Category;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.rmi.PortableRemoteObject;

import static org.junit.Assert.*;

/**
 * Created by steven on 17/11/2016.
 */
public class CategoryDaoTest extends AbstractPersistentTest {

    private CategoryDao cd = null;

    @Before
    public void init(){
        cd = new CategoryDao();
        cd.em = em;
    }

    @Test
    public void shouldCreateACategoty() throws Exception {
        Category category = new Category(1,"Test Test");
        assertNull("Category should not be found", em.find(Category.class, category.getId()));
        cd.createCategory(category);
        assertNotNull("Category should not be found", em.find(Category.class, category.getId()));
    }


    @Test
    public void shouldCreateACategotyEmpty() throws Exception {
        Category category = new Category();
        assertNull("Category should not be found", em.find(Category.class, category.getId()));
        boolean isCreate = cd.createCategory(category);
        assertFalse("Category should be created with category empty", isCreate);
    }

    @Test
    public void shouldFindACategoty() throws Exception {
        Category category = new Category(1,"Test Test");
        assertNull("Category should not be found", em.find(Category.class, category.getId()));
        cd.createCategory(category);
        assertNotNull("Category should not be found", em.find(Category.class, category.getId()));
        Category findCategorie = cd.findCategoryById(category.getId());
        assertEquals("Category should be find with category", findCategorie.getId(), 1);
    }

    @Test
    public void shouldFindACategotyById() throws Exception {
        Category category = new Category(1,"Test Test");
        assertNull("Category should not be found", em.find(Category.class, category.getId()));
        cd.createCategory(category);
        assertNotNull("Category should not be found", em.find(Category.class, category.getId()));
        Category findCategorie = cd.findCategoryById(category.getId());
        assertEquals("Category should be find with category", findCategorie.getId(), 1);
    }

}
