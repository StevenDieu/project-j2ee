package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.model.Newsletter;
import com.rizomm.ipii.steven.model.Product;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ejb.Remote;
import java.util.List;
import java.util.Map;

/**
 * Created by steven on 17/11/2016.
 */
@Remote
public interface INewsletterDao {

    Newsletter createNewsletter(Newsletter newsletter);

    Newsletter findNewsletterByEmail(String email);

    Boolean deleteNewsletter(Newsletter newsletter);
}
