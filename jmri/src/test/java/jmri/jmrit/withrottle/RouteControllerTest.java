// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.withrottle;

import jmri.InstanceManager;
import jmri.NamedBeanHandleManager;
import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Test simple functioning of RouteController
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class RouteControllerTest {

    @Test
    public void testCtor() {
        RouteController panel = new RouteController();
        Assert.assertNotNull("exists", panel );
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        InstanceManager.setDefault(NamedBeanHandleManager.class, new NamedBeanHandleManager());
    }
    
    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }
}
