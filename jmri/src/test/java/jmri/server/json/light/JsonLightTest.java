// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.server.json.light;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 *
 * @author Randall Wood Copyright 2018
 */
public class JsonLightTest {

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }

    @Test
    public void testConstructor() throws Exception {
        try {
            Constructor<JsonLight> constructor;
            constructor = JsonLight.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
            Assert.fail("Instance of JsonLight created");
        } catch (InvocationTargetException ex) {
            // because the constructor throws UnsupportedOperationException, and
            // that is thrown by newInstance() into an InvocationTargetException
            // we pass an InvocationTargetException that is caused by an
            // UnsupportedOperationException and fail everything else by
            // rethrowing the unexepected exception to get a stack trace
            if (!ex.getCause().getClass().equals(UnsupportedOperationException.class)) {
                throw ex;
            }
        }
    }

}
