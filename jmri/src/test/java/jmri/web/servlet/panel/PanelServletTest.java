// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.web.servlet.panel;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Tests for the jmri.web.servlet.panel.PanelServlet class
 *
 * @author Paul Bender Copyright (C) 2012,2016
 */
public class PanelServletTest {

    @Test
    public void testCtor() {
        PanelServlet a = new PanelServlet();
        Assert.assertNotNull(a);
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();

    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }
}
