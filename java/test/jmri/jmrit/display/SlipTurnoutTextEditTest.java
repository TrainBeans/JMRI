// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.display;

import java.awt.GraphicsEnvironment;

import jmri.util.JUnitUtil;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.jupiter.api.*;

/**
 * Test simple functioning of SlipTurnoutTextEdit
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class SlipTurnoutTextEditTest extends jmri.util.JmriJFrameTestBase {

    @Test
    public void initCheck() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Editor ef = new EditorScaffold();
        SlipTurnoutIcon i = new SlipTurnoutIcon(ef);
        // this test (currently) makes sure there are no exceptions
        // thrown when initComponents is called.
        try {
            ((SlipTurnoutTextEdit)frame).initComponents(i, "foo");
        } catch (Exception e) {
            Assert.fail("Exception " + e + " Thrown during initComponents call ");
        }
    }

    @BeforeEach
    @Override
    public void setUp() {
        JUnitUtil.setUp();
        JUnitUtil.resetProfileManager();
        if(!GraphicsEnvironment.isHeadless()){
           frame = new SlipTurnoutTextEdit();
        }
    }

    @AfterEach
    @Override
    public void tearDown() {
        JUnitUtil.deregisterBlockManagerShutdownTask();
        super.tearDown();
    }

}
