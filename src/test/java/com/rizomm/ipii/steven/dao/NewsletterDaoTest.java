package com.rizomm.ipii.steven.dao;

import com.rizomm.ipii.steven.AbstractPersistentTest;
import com.rizomm.ipii.steven.model.Newsletter;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by Steven Dieu on 06/01/2017.
 */
public class NewsletterDaoTest extends AbstractPersistentTest {

    // ======================================
    // =             Attributes             =
    // ======================================

    protected static ValidatorFactory vf;
    protected static Validator validator;

    // ======================================
    // =          Lifecycle Methods         =
    // ======================================

    @BeforeClass
    public static void initClass() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }


    private NewsletterDao ND = null;

    @Before
    public void init() {
        ND = new NewsletterDao();
        ND.em = em;
        ND.isNotTest = false;
    }

    @Test
    public void shouldCheckEmailArobase() {

        Newsletter newsletter = new Newsletter("jsmithgmail.com");

        Set<ConstraintViolation<Newsletter>> validate = validator.validate(newsletter);
        displayContraintViolations(validate);
        assertEquals(1, validate.size());
        assertEquals("Ceci n'est pas une adresse email valide", validate.iterator().next().getMessage());
    }

    @Test
    public void shouldCheckEmailDot() {

        Newsletter newsletter = new Newsletter("jsmith@gmailcom");

        Set<ConstraintViolation<Newsletter>> validate = validator.validate(newsletter);
        displayContraintViolations(validate);
        assertEquals(1, validate.size());
        assertEquals("Ceci n'est pas une adresse email valide", validate.iterator().next().getMessage());
    }

    @Test
    public void shouldValidEmail() {

        Newsletter newsletter = new Newsletter("jsmith@gmail.com");

        Set<ConstraintViolation<Newsletter>> validate = validator.validate(newsletter);
        displayContraintViolations(validate);
        assertEquals(0, validate.size());
    }

    @Test
    public void shouldCheckEmailCustomMessage() {

        Newsletter newsletter = new Newsletter("jsmithgmail.com");

        Set<ConstraintViolation<Newsletter>> validate = validator.validate(newsletter);
        displayContraintViolations(validate);
        assertEquals(1, validate.size());
        assertEquals("Ceci n'est pas une adresse email valide", validate.iterator().next().getMessage());
    }

    @Test
    public void shouldCreateANewsletter() throws Exception {
        Newsletter newsletter = new Newsletter("jsmith@gmail.com");
        assertNull("Newsletter should be found", em.find(Newsletter.class, newsletter.getEmail()));

        ND.createNewsletter(newsletter);
        assertNotNull("Newsletter should not be found", em.find(Newsletter.class, newsletter.getEmail()));
    }


    @Test
    public void shouldCreateANewsletterEmpty() throws Exception {
        Newsletter newsletter = new Newsletter("jsmith@gmail.com");
        assertNull("Newsletter should not be found", em.find(Newsletter.class, newsletter.getEmail()));

        Newsletter isCreate = ND.createNewsletter(newsletter);
        assertNotNull("Newsletter should not be found", em.find(Newsletter.class, newsletter.getEmail()));
    }

    @Test
    public void shouldDeleteNewsletter() throws Exception {
        Newsletter newsletter = new Newsletter("jsmith@gmail.com");
        Newsletter newsletter2 = new Newsletter("jsmith2@gmail.com");
        assertNull("Newsletter should not be found", em.find(Newsletter.class, newsletter.getEmail()));
        assertNull("Newsletter should not be found", em.find(Newsletter.class, newsletter2.getEmail()));

        tx.begin();
        ND.createNewsletter(newsletter);
        ND.createNewsletter(newsletter2);

        Boolean isDeleted = ND.deleteNewsletter(newsletter);
        assertTrue("Newsletter should not be not deleted", isDeleted);
        tx.commit();

        Newsletter returnNewsletter1 = ND.findNewsletterByEmail(newsletter.getEmail());
        Newsletter returnNewsletter2 = ND.findNewsletterByEmail(newsletter2.getEmail());
        assertNull("Newsletter should be not a delete Newsletter", returnNewsletter1);
        assertNotNull("Newsletter should be delete Newsletter", returnNewsletter2);

        tx.begin();
        ND.deleteNewsletter(returnNewsletter2);
        tx.commit();

        assertNull("Newsletter should be not a delete Newsletter", returnNewsletter1);

    }


    private void displayContraintViolations(Set<ConstraintViolation<Newsletter>> constraintViolations) {
        for (ConstraintViolation constraintViolation : constraintViolations) {
            System.out.println("### " + constraintViolation.getRootBeanClass().getSimpleName() +
                    "." + constraintViolation.getPropertyPath() + " - Invalid Value = " + constraintViolation.getInvalidValue() + " - Error Msg = " + constraintViolation.getMessage());

        }
    }

}
