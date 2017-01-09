package com.rizomm.ipii.steven.model.beanValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


/**
 * Interface Email for test if email is correct
 *
 * @author steven
 * Created on 09/01/2017
 */
@NotNull
@Size(min = 7)
@Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
        + "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*"
        + "@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    /**
     * Method message is the email is not correct
     *
     * @return String
     */
    String message() default "Ceci n'est pas une adresse email valide";

    /**
     * Method groups
     *
     * @return Class<?>[]
     */
    Class<?>[] groups() default {};

    /**
     * Method payload
     * @return Class<? extends Payload>[]
     */
    Class<? extends Payload>[] payload() default {};
}
