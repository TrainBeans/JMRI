// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.ussctc;

import jmri.util.JUnitUtil;
import jmri.*;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Tests for PhysicalBell class in the jmri.jmrit.ussctc package
 *
 * @author Bob Jacobsen Copyright 2007
 */
public class PhysicalBellTest {

    @Test
    public void testConstruction() {
        new PhysicalBell("Bell output");
    }
 
    @Test 
    public void testBellStroke() {
        layoutTurnout.setCommandedState(Turnout.CLOSED);
 
        PhysicalBell bell = new PhysicalBell("Bell output");

        Assert.assertEquals(Turnout.CLOSED, layoutTurnout.getState());
        bell.ring();
        Assert.assertEquals(Turnout.THROWN, layoutTurnout.getState());
        jmri.util.JUnitUtil.waitFor(()->{return layoutTurnout.getState()==Turnout.CLOSED;}, "stroke didn't end");
    }
    
    Turnout layoutTurnout;
    
    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        JUnitUtil.resetProfileManager();
        JUnitUtil.initConfigureManager();
        JUnitUtil.initInternalTurnoutManager();
        
        layoutTurnout = InstanceManager.getDefault(TurnoutManager.class).provideTurnout("IT1"); layoutTurnout.setUserName("Bell output");
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }

}
