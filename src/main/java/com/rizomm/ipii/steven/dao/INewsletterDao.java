package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Newsletter;

import javax.ejb.Remote;

/**
 * Created by steven on 17/11/2016.
 */
@Remote
public interface INewsletterDao {

    /**
     * Create a newsletter
     *
     * @param newsletter
     * @return
     */
    Newsletter createNewsletter(Newsletter newsletter);

    /**
     * Find a newsletter by email
     *
     * @param email
     * @return
     */
    Newsletter findNewsletterByEmail(String email);

    /**
     * Delete newsletter by a model newsletter
     *
     * @param newsletter
     * @return
     */
    Boolean deleteNewsletter(Newsletter newsletter);
}
