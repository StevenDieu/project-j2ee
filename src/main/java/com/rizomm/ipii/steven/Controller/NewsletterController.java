package com.rizomm.ipii.steven.Controller;

import com.rizomm.ipii.steven.dao.INewsletterDao;
import com.rizomm.ipii.steven.model.Newsletter;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ManagedBean
@RequestScoped
public class NewsletterController implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private INewsletterDao NR;

    private Newsletter newsletter;
    private String messageNewsletter = "";

    public void addNewsletter() {

    }

    public Newsletter getNewsletter() {
        return newsletter;
    }

    public String getMessageNewsletter() {
        return messageNewsletter;
    }

}