// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.nce.macro;

import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;

/**
 * Test simple functioning of NceMacroGenPanel
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class NceMacroGenPanelTest extends jmri.util.swing.JmriPanelTest {


    @Override
    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        panel = new NceMacroGenPanel();
        helpTarget="package.jmri.jmrix.nce.macro.NceMacroEditFrame";
        title="NCE_: " + Bundle.getMessage("TitleNceMacroGen");
    }

    @Override
    @AfterEach
    public void tearDown() {        JUnitUtil.tearDown();    }
}
