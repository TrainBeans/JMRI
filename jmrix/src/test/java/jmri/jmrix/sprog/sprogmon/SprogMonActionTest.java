// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.sprog.sprogmon;

import java.awt.GraphicsEnvironment;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.Assume;

import jmri.jmrix.sprog.SprogSystemConnectionMemo;

/**
 * Test simple functioning of SprogMonAction  
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class SprogMonActionTest {


    @Test
    public void testCtor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless()); 
        SprogSystemConnectionMemo memo = new SprogSystemConnectionMemo();
        SprogMonAction action = new SprogMonAction("SPROG Action Test",memo);
        Assert.assertNotNull("exists", action);
        memo.dispose();
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
    }

    @AfterEach
    public void tearDown() {        JUnitUtil.tearDown();    }
}
