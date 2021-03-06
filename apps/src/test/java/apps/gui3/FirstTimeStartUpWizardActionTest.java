// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package apps.gui3;

import java.awt.GraphicsEnvironment;

import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;
import org.junit.Assert;
import org.junit.Assume;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class FirstTimeStartUpWizardActionTest {

    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        FirstTimeStartUpWizardAction t = new FirstTimeStartUpWizardAction("test ation");
        Assert.assertNotNull("exists", t);
        t.dispose();
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }

    // private final static Logger log = LoggerFactory.getLogger(FirstTimeStartUpWizardActionTest.class);
}
