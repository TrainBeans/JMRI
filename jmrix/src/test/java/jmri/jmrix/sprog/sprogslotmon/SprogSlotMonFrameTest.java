// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.sprog.sprogslotmon;

import java.awt.GraphicsEnvironment;

import jmri.jmrix.sprog.SprogSystemConnectionMemo;
import jmri.jmrix.sprog.SprogTrafficControlScaffold;
import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;

/**
 * Test simple functioning of SprogSlotMonFrame
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class SprogSlotMonFrameTest extends jmri.util.JmriJFrameTestBase {

    private SprogTrafficControlScaffold stcs = null;
    private SprogSystemConnectionMemo m = null;

    @BeforeEach
    @Override
    public void setUp() {
        JUnitUtil.setUp();
        jmri.util.JUnitUtil.resetProfileManager();

        m = new jmri.jmrix.sprog.SprogSystemConnectionMemo(jmri.jmrix.sprog.SprogConstants.SprogMode.OPS);
        stcs = new SprogTrafficControlScaffold(m);
        m.setSprogTrafficController(stcs);
        m.configureCommandStation();
        if (!GraphicsEnvironment.isHeadless()) {
            frame = new SprogSlotMonFrame(m);
        }
    }

    @AfterEach
    @Override
    public void tearDown() {
        super.tearDown();
        m.dispose();
        m = null;
        stcs.dispose();
        stcs = null;
        JUnitUtil.clearShutDownManager(); // put in place because AbstractMRTrafficController implementing subclass was not terminated properly
    }
}


