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

    public Newsletter() {}

    public Newsletter(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
