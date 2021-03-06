// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.display;

import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

import jmri.InstanceManager;
import jmri.ReporterManager;
import jmri.util.swing.NamedIcon;
import jmri.util.JUnitUtil;

import org.junit.Assume;
import org.junit.jupiter.api.*;

/**
 * Test the ReporterIcon.
 *
 * @author Bob Jacobsen Copyright 2007
 */
public class ReporterIconTest extends PositionableTestBase {

    private ReporterIcon to = null;

    @Test
    public void testShowSysName() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        JFrame jf = new JFrame();
        jf.getContentPane().setLayout(new java.awt.FlowLayout());

        jf.getContentPane().add(to);

        jf.pack();
        jf.setVisible(true);
        JUnitUtil.dispose(jf);
    }

    @Test
    public void testShowNumericAddress() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        JFrame jf = new JFrame();
        jf.getContentPane().setLayout(new java.awt.FlowLayout());

        jf.getContentPane().add(to);


        jf.pack();
        jf.setVisible(true);
    }

    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        JUnitUtil.initReporterManager();
        if (!GraphicsEnvironment.isHeadless()) {
            editor = new EditorScaffold();
            p = to = new ReporterIcon(editor);

            // create objects to test
            InstanceManager.getDefault(ReporterManager.class).provideReporter("IR1");
            to.setReporter("IR1");
            InstanceManager.getDefault(ReporterManager.class).provideReporter("IR1").setReport("data");
            NamedIcon icon = new NamedIcon("resources/icons/redTransparentBox.gif", "box"); // 13x13
            to.setIcon(icon);
        }
    }

    @Override
    @AfterEach
    public void tearDown() {
        to = null;
        super.tearDown();
    }

    // private final static Logger log = LoggerFactory.getLogger(TurnoutIconTest.class);
}
