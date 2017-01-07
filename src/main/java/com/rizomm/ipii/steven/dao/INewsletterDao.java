package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Newsletter;

import javax.ejb.Remote;

/**
 * Created by steven on 17/11/2016.
 */
@Remote
public interface INewsletterDao {

    Newsletter createNewsletter(Newsletter newsletter);

    Newsletter findNewsletterByEmail(String email);

    Boolean deleteNewsletter(Newsletter newsletter);
}
