// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.simplelightctrl;

import java.awt.GraphicsEnvironment;

import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;

/**
 * Test simple functioning of SimpleLightCtrlFrame
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class SimpleLightCtrlFrameTest extends jmri.util.JmriJFrameTestBase {

    @BeforeEach
    @Override
    public void setUp() {
        JUnitUtil.setUp();
        JUnitUtil.resetProfileManager();
        if (!GraphicsEnvironment.isHeadless()) {
            frame = new SimpleLightCtrlFrame();
        }
    }

    @AfterEach
    @Override
    public void tearDown() {
        super.tearDown();
    }
}
