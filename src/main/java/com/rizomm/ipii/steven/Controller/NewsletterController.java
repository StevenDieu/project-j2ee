package com.rizomm.ipii.steven.Controller;

import com.rizomm.ipii.steven.dao.INewsletterDao;
import com.rizomm.ipii.steven.helper.Utils;
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

    private String email;
    private String messageNewsletter = "";
    private String styleClassMessage = "alert-danger";

    public void addNewsletter() {
        if (Utils.isEmpty(NR.findNewsletterByEmail(email))) {
            final Newsletter newsletter = new Newsletter(email);
            NR.createNewsletter(newsletter);
            styleClassMessage = "alert-success";
            messageNewsletter = "Vous êtes désormais inscrit à la Newslettter";
            email = "";
        } else {
            styleClassMessage = "alert-danger";
            messageNewsletter = "Cette adresse mail est déja utilisé pour la newsletter.";
        }
    }

    public String getStyleClassMessage() {
        return styleClassMessage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessageNewsletter() {
        return messageNewsletter;
    }

}