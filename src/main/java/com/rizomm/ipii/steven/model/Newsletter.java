package com.rizomm.ipii.steven.model;

import com.rizomm.ipii.steven.model.beanValidation.Email;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Steven Dieu on 06/01/2017.
 */
@Entity
public class Newsletter implements Serializable {

    @Id
    @Email
    private String email;

    /**
     * Constructor Newsletter creates a new Newsletter instance.
     */
    public Newsletter() {
    }

    /**
     * Constructor Newsletter creates a new Newsletter instance.
     *
     * @param email of type String
     */
    public Newsletter(String email) {
        this.email = email;
    }

    /**
     * Method getEmail returns the email of this Newsletter object.
     *
     * @return the email (type String) of this Newsletter object.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method setEmail sets the email of this Newsletter object.
     *
     * @param email the email of this Newsletter object.
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
