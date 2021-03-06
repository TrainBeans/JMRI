// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.operations.trains.excel;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.Assume;

import jmri.jmrit.operations.OperationsTestCase;
import jmri.util.JUnitOperationsUtil;
import jmri.util.JUnitUtil;
import jmri.util.JmriJFrame;

/**
 * @author Paul Bender Copyright (C) 2017
 */
public class SetupExcelProgramFrameActionTest extends OperationsTestCase {

    @Test
    public void testCTor() {
        SetupExcelProgramFrameAction t = new SetupExcelProgramFrameAction();
        Assert.assertNotNull("exists", t);
    }

    @Test
    public void testAction() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        SetupExcelProgramFrameAction sepfa = new SetupExcelProgramFrameAction();
        Assert.assertNotNull("exists", sepfa);
        
        sepfa.actionPerformed(new ActionEvent(this, 0, null));
        
        JmriJFrame excelFrame = JmriJFrame.getFrame(Bundle.getMessage("MenuItemSetupExcelProgram"));
        Assert.assertNotNull("exists", excelFrame);
        JUnitUtil.dispose(excelFrame);
        JUnitOperationsUtil.checkOperationsShutDownTask();
    }
}
