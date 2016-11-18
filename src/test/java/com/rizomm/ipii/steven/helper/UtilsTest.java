package com.rizomm.ipii.steven.helper;

import com.rizomm.ipii.steven.model.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by steven on 17/11/2016.
 */
public class UtilsTest {


    @Test
    public void shouldStringIsEmpty() throws Exception {
        assertEquals("The string is empty and return false", Utils.isEmpty(""), true);
        assertEquals("The string is null and return false", Utils.isEmpty(null), true);
        assertEquals("The string is not empty and return true", Utils.isEmpty("test"), false);
    }
}
