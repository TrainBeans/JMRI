// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.beantable.signalmast;

import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;
import org.junit.Assert;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class SignalMastRepeaterPanelTest {

    @Test
    public void testCTor() {
        SignalMastRepeaterPanel t = new SignalMastRepeaterPanel();
        Assert.assertNotNull("exists",t);
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }

    // private final static Logger log = LoggerFactory.getLogger(SignalMastRepeaterPanelTest.class);

}
