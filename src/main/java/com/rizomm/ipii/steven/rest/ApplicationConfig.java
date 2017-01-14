package com.rizomm.ipii.steven.rest;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Class ApplicationConfig ...
 *
 * @author steven
 *         Created on 09/01/2017
 */
@ApplicationPath("json")
public class ApplicationConfig extends Application {

    private final Set<Class<?>> classes;

    /**
     * Constructor ApplicationConfig creates a new ApplicationConfig instance.
     */
    public ApplicationConfig() {
        HashSet<Class<?>> c = new HashSet<>();
        c.add(ProductRest.class);
        c.add(CategoryRest.class);
        c.add(OrderRest.class);

        c.add(MOXyJsonProvider.class);

        classes = Collections.unmodifiableSet(c);
    }

    /**
     * Method getClasses returns the classes of this ApplicationConfig object.
     *
     * @return the classes (type Set<Class<?>>) of this ApplicationConfig object.
     */
    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

}