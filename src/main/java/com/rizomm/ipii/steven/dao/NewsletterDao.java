package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Newsletter;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.rizomm.ipii.steven.helper.Utils.isEmpty;
import static com.rizomm.ipii.steven.helper.Utils.isNotEmpty;

/**
 * Created by steven on 17/11/2016.
 */
@Stateless
@Named
public class NewsletterDao implements INewsletterDao {

    @PersistenceContext(unitName = "projectJ2ee")
    protected EntityManager em;
    protected boolean isNotTest = true;

    /**
     * Create a newsletter
     *
     * @param newsletter
     * @return
     */
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

    /**
     * Find a newsletter by email
     *
     * @param email
     * @return
     */
    @Override
    public Newsletter findNewsletterByEmail(final String email) {
        final Newsletter findNewsletter = em.find(Newsletter.class, email);
        return findNewsletter;
    }

    /**
     * Delete newsletter by a model newsletter
     *
     * @param newsletter
     * @return
     */
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
