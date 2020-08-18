// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.display.controlPanelEditor.shape.configurexml;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * PositionableCircleXmlTest.java
 *
 * Test for the PositionableCircleXml class
 *
 * @author   Paul Bender  Copyright (C) 2016
 */
public class PositionableCircleXmlTest {

    @Test
    public void testCtor(){
      Assert.assertNotNull("PositionableCircleXml constructor",new PositionableCircleXml());
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

