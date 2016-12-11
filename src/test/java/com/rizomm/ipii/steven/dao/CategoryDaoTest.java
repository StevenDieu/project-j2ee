package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.AbstractPersistentTest;
import com.rizomm.ipii.steven.model.Category;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by steven on 17/11/2016.
 */
public class CategoryDaoTest extends AbstractPersistentTest {

    private CategoryDao cd = null;

    @Before
    public void init() {
        cd = new CategoryDao();
        cd.em = em;
        cd.isNotTest = false;
    }

    @Test
    public void shouldCreateACategoty() throws Exception {
        Category category = new Category(1, "Test Test");
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
        Category category = new Category(1, "Test Test");
        assertNull("Category should not be found", em.find(Category.class, category.getId()));

        cd.createCategory(category);
        assertNotNull("Category should not be found", em.find(Category.class, category.getId()));

        Category findCategorie = cd.findCategoryById(category.getId());
        assertEquals("Category should be find with category", findCategorie.getId(), 1);
    }

    @Test
    public void shouldFindACategotyById() throws Exception {
        Category category = new Category(1, "Test Test");
        assertNull("Category should not be found", em.find(Category.class, category.getId()));

        cd.createCategory(category);
        assertNotNull("Category should not be found", em.find(Category.class, category.getId()));

        Category findCategorie = cd.findCategoryById(category.getId());
        assertEquals("Category should be find with category", findCategorie.getId(), 1);
    }

    @Test
    public void shouldFindAllCategoty() throws Exception {
        Category category = new Category(1, "Test Test");
        Category category2 = new Category(2, "Test Test");
        assertNull("Category should not be found", em.find(Category.class, category.getId()));

        tx.begin();
        cd.createCategory(category);
        cd.createCategory(category2);
        tx.commit();

        assertNotNull("Category should not be found", em.find(Category.class, category.getId()));

        List<Category> allCategorie = cd.findAllCategory();
        assertEquals("Nummber to search category", allCategorie.size(), 2);

        deleteAllCategory();


    }

    @Test
    public void shouldDeleteCategory() throws Exception {
        Category category = new Category(1, "Test Test");
        Category category2 = new Category(2, "Test Test");
        assertNull("Category should not be found", em.find(Category.class, category.getId()));

        tx.begin();
        cd.createCategory(category);
        cd.createCategory(category2);

        Boolean isDeleted = cd.deleteCategory(category);
        assertTrue("Category should not be not deleted", isDeleted);
        tx.commit();

        List<Category> allCategorie = cd.findAllCategory();
        assertEquals("Nummber to search category", allCategorie.size(), 1);
        assertEquals("Category should be not a greate category", allCategorie.get(0).getId(), 2);

        deleteAllCategory();
    }

    @Test
    public void shouldDeleteCategoryById() throws Exception {
        Category category = new Category(1, "Test Test");
        Category category2 = new Category(2, "Test Test");
        assertNull("Category should not be found", em.find(Category.class, category.getId()));

        tx.begin();

        cd.createCategory(category);
        cd.createCategory(category2);

        Boolean isDeleted = cd.deleteCategoryById(category2.getId());
        assertTrue("Category should not be not deleted", isDeleted);

        tx.commit();

        List<Category> allCategorie = cd.findAllCategory();
        assertEquals("Nummber to search category", allCategorie.size(), 1);
        assertEquals("Category should be not a greate category", allCategorie.get(0).getId(), 1);

        deleteAllCategory();
    }


    @Test
    public void shouldUpdateCategory() throws Exception {
        Category category = new Category(1, "Test Test");
        Category category2 = new Category(1, "Test2 Test2");
        assertNull("Category should not be found", em.find(Category.class, category.getId()));

        tx.begin();
        cd.createCategory(category);
        tx.commit();

        List<Category> allCategorie = cd.findAllCategory();
        assertEquals("Category is not correct", allCategorie.get(0).getLabel(), "Test Test");

        tx.begin();
        cd.updateCategory(category2);
        tx.commit();

        allCategorie = cd.findAllCategory();
        assertEquals("Category is not correct", allCategorie.get(0).getLabel(), "Test2 Test2");

        deleteAllCategory();
    }

    private void deleteAllCategory() {
        tx.begin();
        cd.deleteAllCategory();
        tx.commit();
    }

}
