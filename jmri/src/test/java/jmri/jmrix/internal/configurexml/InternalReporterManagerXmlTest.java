// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.internal.configurexml;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * InternalReporterManagerXmlTest.java
 *
 * Test for the InternalReporterManagerXml class
 *
 * @author   Paul Bender  Copyright (C) 2016
 */
public class InternalReporterManagerXmlTest {

    @Test
    public void testCtor(){
      Assert.assertNotNull("InternalReporterManagerXml constructor",new InternalReporterManagerXml());
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

