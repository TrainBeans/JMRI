// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.can.adapters.gridconnect.sproggen5.serialdriver;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Tests for CanSprogConnectionConfig class.
 *
 * @author Andrew Crosland (C) 2020
 **/
public class CanSprogConnectionConfigTest extends jmri.jmrix.AbstractSerialConnectionConfigTestBase {

    @Test
    public void testCTor() {
        CanSprogConnectionConfig c = new CanSprogConnectionConfig();
        Assert.assertNotNull("exists",c);
    }
    
   @BeforeEach
   @Override
   public void setUp() {
        JUnitUtil.setUp();

        JUnitUtil.initDefaultUserMessagePreferences();
        cc = new CanSprogConnectionConfig();
   }

   @AfterEach
   @Override
   public void tearDown(){
        cc = null;
        JUnitUtil.tearDown();
   }

}
