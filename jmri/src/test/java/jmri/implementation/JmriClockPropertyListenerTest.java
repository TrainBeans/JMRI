// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.implementation;

import jmri.Conditional;
import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;
import org.junit.Assert;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class JmriClockPropertyListenerTest {

    @Test
    public void testCTor() {
        JmriClockPropertyListener t =
                new JmriClockPropertyListener("foo",0,"bar",
                        Conditional.Type.SENSOR_ACTIVE,new DefaultConditional("foo"),0,0);
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

    // private final static Logger log = LoggerFactory.getLogger(JmriClockPropertyListenerTest.class);

}
