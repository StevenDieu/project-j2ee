package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Category;
import com.rizomm.ipii.steven.model.Newsletter;
import com.rizomm.ipii.steven.model.Product;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.rizomm.ipii.steven.helper.Utils.*;
import static com.rizomm.ipii.steven.model.Product.*;

/**
 * Created by steven on 17/11/2016.
 */
@Stateless
@Named
public class NewsletterDao implements INewsletterDao {

    @PersistenceContext(unitName = "projectJ2ee")
    protected EntityManager em;
    protected boolean isNotTest = true;

    @Override
    public Newsletter createNewsletter(final Newsletter newsletter) {
        if (isNotEmpty(newsletter.getEmail())) {
            em.persist(newsletter);
            if (isNotTest) {
                em.flush();
            }
            return newsletter;
        }
        return null;
    }

    @Override
    public Newsletter findNewsletterByEmail(final String email) {
        final Newsletter findNewsletter = em.find(Newsletter.class, email);
        return findNewsletter;
    }

    @Override
    public Boolean deleteNewsletter(final Newsletter newsletter) {
        em.remove(newsletter);
        final Newsletter findNewsletter = em.find(Newsletter.class, newsletter.getEmail());
        if (isEmpty(findNewsletter)) {
            return true;
        }
        return false;
    }
}
