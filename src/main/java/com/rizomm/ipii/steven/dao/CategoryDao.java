package com.rizomm.ipii.steven.dao;


import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Category;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

import static com.rizomm.ipii.steven.model.Category.*;

/**
 * Created by steven on 17/11/2016.
 */

@Stateless
@Local
public class CategoryDao implements ICategoryDao{

    @PersistenceContext(unitName = "projectJ2ee")
    protected EntityManager em;
    protected boolean isNotTest = true;

    @Override
    public boolean createCategory(Category category) {
        if(Utils.isNotEmpty(category.getLabel())){
            em.persist(category);
            return true;
        }
        return false;
    }

    @Override
    public Category findCategoryById(int idCategory) {
        Category findCategory = em.find(Category.class, idCategory);
        return findCategory;
    }

    @Override
    public List<Category> findAllCategory() {
        TypedQuery<Category> query = em.createNamedQuery(FIND_ALL, Category.class);
        if(isNotTest){
            em.joinTransaction();
        }
        return query.getResultList();
    }

    @Override
    public void deleteAllCategory() {
        em.createNamedQuery(DELETE_ALL, Category.class).executeUpdate();
    }

    @Override
    public Boolean deleteCategoryById(int idCategory) {
        Category category = em.find(Category.class, idCategory);
        if(Utils.isNotEmpty(category)){
            return deleteCategory(category);
        }
        return false;
    }

    @Override
    public Boolean deleteCategory(Category category) {
        em.remove(category);
        Category findCategory = em.find(Category.class, category.getId());
        if(Utils.isEmpty(findCategory)){
            return true;
        }
        return false;
    }

    @Override
    public Category updateCategory(Category category) {
        return em.merge(category);
    }

}
