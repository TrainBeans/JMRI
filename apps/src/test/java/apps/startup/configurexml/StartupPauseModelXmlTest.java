// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package apps.startup.configurexml;

import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;
import org.junit.Assert;

/**
 * StartupPauseModelXmlTest.java
 *
 * Test for the StartupPauseModelXml class
 *
 * @author   Paul Bender  Copyright (C) 2016
 */
public class StartupPauseModelXmlTest {

    @Test
    public void testCtor(){
      Assert.assertNotNull("StartupPauseModelXml constructor",new StartupPauseModelXml());
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

