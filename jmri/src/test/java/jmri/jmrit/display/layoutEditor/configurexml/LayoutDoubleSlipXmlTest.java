// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.display.layoutEditor.configurexml;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * LayoutSlipXmlTest.java
 *
 * Test for the LayoutSlipXml class
 *
 * @author   George Warner  Copyright (C) 2017
 */
public class LayoutDoubleSlipXmlTest {

    @Test
    public void testCtor(){
      Assert.assertNotNull("LayoutSlipXml constructor", new LayoutDoubleSlipXml());
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
