// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package apps.configurexml;

import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;
import org.junit.Assert;

/**
 * CreateButtonModelXmlTest.java
 *
 * Test for the CreateButtonModelXml class
 *
 * @author   Paul Bender  Copyright (C) 2016
 */
public class CreateButtonModelXmlTest {

    @Test
    public void testCtor(){
      Assert.assertNotNull("CreateButtonModelXml constructor",new CreateButtonModelXml());
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

