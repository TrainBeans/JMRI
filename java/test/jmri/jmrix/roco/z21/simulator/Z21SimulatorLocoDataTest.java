// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.roco.z21.simulator;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Z21SimulatorLocoDataTest.java
 * 
 * Test for the jmri.jmrix.roco.z21.simulator.z21SimulatorLocoData
 * class
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class Z21SimulatorLocoDataTest {
        
    @Test
    public void testCtor() {
        Z21SimulatorLocoData a = new Z21SimulatorLocoData((byte)0x00,(byte)0x02,(byte)0x03);
        Assert.assertNotNull(a);
    }

    @Test
    public void shortAddressTest() {
        Z21SimulatorLocoData a = new Z21SimulatorLocoData((byte)0x00,(byte)0x02,(byte)0x03);
        // test the getter functions.
        Assert.assertEquals("addr_msb",(byte)0x00,a.getAddressMsb());
        Assert.assertEquals("addr_lsb",(byte)0x02,a.getAddressLsb());
        Assert.assertEquals("speed byte",(byte)0x03,a.getSpeed());
    }

    @Test
    public void longAddressTest() {
        Z21SimulatorLocoData a = new Z21SimulatorLocoData((byte)0xCF,(byte)0x02,(byte)0x03);
        // test the getter functions.
        Assert.assertEquals("addr_msb",0x0F,a.getAddressMsb());
        Assert.assertEquals("addr_lsb",0x02,a.getAddressLsb());
        Assert.assertEquals("speed byte",0x03,a.getSpeed());
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
