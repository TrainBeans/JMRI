// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.pi.configurexml;

import java.util.HashMap;
import java.util.Map;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 *
 * @author Randall Wood Copyright 2017
 */
public class RaspberryPiClassMigrationTest {

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        JUnitUtil.resetInstanceManager();
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.resetInstanceManager();
        JUnitUtil.tearDown();
    }

    @Test
    public void testGetMigrations() {
        RaspberryPiClassMigration instance = new RaspberryPiClassMigration();
        Map<String, String> expResult = new HashMap<>();
        expResult.put("jmri.jmrix.pi.configurexml.ConnectionConfigXml", "jmri.jmrix.pi.configurexml.RaspberryPiConnectionConfigXml");
        Assert.assertEquals(expResult, instance.getMigrations());
    }

}
