package com.rizomm.ipii.steven.Controller;

import com.rizomm.ipii.steven.dao.INewsletterDao;
import com.rizomm.ipii.steven.helper.Utils;
import com.rizomm.ipii.steven.model.Newsletter;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Class NewsletterController ...
 *
 * @author steven
 *         Created on 09/01/2017
 */
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

    /**
     * Method addNewsletter to add email on newsletter
     */
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

    /**
     * Method getStyleClassMessage returns the styleClassMessage of this NewsletterController object.
     *
     * @return the styleClassMessage (type String) of this NewsletterController object.
     */
    public String getStyleClassMessage() {
        return styleClassMessage;
    }

    /**
     * Method getEmail returns the email of this NewsletterController object.
     *
     * @return the email (type String) of this NewsletterController object.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method setEmail sets the email of this NewsletterController object.
     *
     * @param email the email of this NewsletterController object.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method getMessageNewsletter returns the messageNewsletter of this NewsletterController object.
     *
     * @return the messageNewsletter (type String) of this NewsletterController object.
     */
    public String getMessageNewsletter() {
        return messageNewsletter;
    }

}