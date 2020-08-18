// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.beantable;

import java.awt.GraphicsEnvironment;
import java.util.ResourceBundle;
import javax.swing.JFrame;
import javax.swing.JTextField;

import jmri.InstanceManager;
import jmri.Section;
import jmri.util.JUnitUtil;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.jupiter.api.*;
import org.netbeans.jemmy.operators.*;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class SectionTableActionTest extends AbstractTableActionBase<Section> {

    @Test
    public void testCTor() {
        Assert.assertNotNull("exists", a);
    }

    @Override
    public String getTableFrameName() {
        return Bundle.getMessage("TitleSectionTable");
    }

    @Override
    @Test
    public void testGetClassDescription() {
        Assert.assertEquals("Section Table Action class description", "Section Table", a.getClassDescription());
    }

    /**
     * Check the return value of includeAddButton. The table generated by this
     * action includes an Add Button.
     */
    @Override
    @Test
    public void testIncludeAddButton() {
        Assert.assertTrue("Default include add button", a.includeAddButton());
    }

    @Override
    public String getAddFrameName() {
        return Bundle.getMessage("TitleAddSection");
    }

    @Test
    @Override
    public void testAddThroughDialog() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Assume.assumeTrue(a.includeAddButton());
        a.actionPerformed(null);
        JFrame f = JFrameOperator.waitJFrame(getTableFrameName(), true, true);

        // find the "Add... " button and press it.
        jmri.util.swing.JemmyUtil.pressButton(new JFrameOperator(f), Bundle.getMessage("ButtonAdd"));
        new org.netbeans.jemmy.QueueTool().waitEmpty();
        JFrame f1 = JFrameOperator.waitJFrame(getAddFrameName(), true, true);
        JFrameOperator jf = new JFrameOperator(f1);
        //Enter 1 in the text field labeled "System Name:"
        JLabelOperator jlo = new JLabelOperator(jf, Bundle.getMessage("LabelSystemName"));
        ((JTextField) jlo.getLabelFor()).setText("1");
        //press the "Add Selected Block" button to add the only defined block
        jmri.util.swing.JemmyUtil.pressButton(jf, ResourceBundle.getBundle("jmri.jmrit.beantable.SectionTransitTableBundle").getString("AddBlockButton"));
        //and press create
        jmri.util.swing.JemmyUtil.pressButton(jf, Bundle.getMessage("ButtonCreate"));
        jmri.util.JUnitAppender.suppressWarnMessage("Block IB12 does not have a user name,may not work correctly in Section IY:AUTO:0001");
        JUnitUtil.dispose(f1);
        JUnitUtil.dispose(f);
    }

    @Test
    @Override
    public void testEditButton() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        Assume.assumeTrue(a.includeAddButton());
        a.actionPerformed(null);
        JFrame f = JFrameOperator.waitJFrame(getTableFrameName(), true, true);

        // find the "Add... " button and press it.
        jmri.util.swing.JemmyUtil.pressButton(new JFrameOperator(f), Bundle.getMessage("ButtonAdd"));
        JFrame f1 = JFrameOperator.waitJFrame(getAddFrameName(), true, true);
        JFrameOperator jf = new JFrameOperator(f1);
        //Enter 1 in the text field labeled "System Name:"

        JLabelOperator jlo = new JLabelOperator(jf, Bundle.getMessage("LabelSystemName"));
        ((JTextField) jlo.getLabelFor()).setText("1");
        //press the "Add Selected Block" button to add the only defined block
        jmri.util.swing.JemmyUtil.pressButton(jf, ResourceBundle.getBundle("jmri.jmrit.beantable.SectionTransitTableBundle").getString("AddBlockButton"));
        //and press create
        jmri.util.swing.JemmyUtil.pressButton(jf, Bundle.getMessage("ButtonCreate"));
        jmri.util.JUnitAppender.suppressWarnMessage("Block IB12 does not have a user name,may not work correctly in Section IY:AUTO:0001");

        JFrameOperator jfo = new JFrameOperator(f);

        JTableOperator tbl = new JTableOperator(jfo, 0);
        // find the "Edit" button and press it.  This is in the table body.
        tbl.clickOnCell(0, BeanTableDataModel.NUMCOLUMN + 2);
        JFrame f2 = JFrameOperator.waitJFrame(getEditFrameName(), true, true);
        jmri.util.swing.JemmyUtil.pressButton(new JFrameOperator(f2), Bundle.getMessage("ButtonCancel"));
        JUnitUtil.dispose(f2);
        JUnitUtil.dispose(f1);
        JUnitUtil.dispose(f);
    }

    @Override
    public String getEditFrameName() {
        return "Edit Section";
    }

    @Override
    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        jmri.util.JUnitUtil.resetProfileManager();
        helpTarget = "package.jmri.jmrit.beantable.SectionTable";
        InstanceManager.setDefault(jmri.BlockManager.class, new jmri.BlockManager());
        a = new SectionTableAction();
        InstanceManager.getDefault(jmri.BlockManager.class).provideBlock("IB12");

    }

    @Override
    @AfterEach
    public void tearDown() {
        JUnitUtil.deregisterBlockManagerShutdownTask();
        JUnitUtil.tearDown();
    }

    // private final static Logger log = LoggerFactory.getLogger(SectionTableActionTest.class);
}
