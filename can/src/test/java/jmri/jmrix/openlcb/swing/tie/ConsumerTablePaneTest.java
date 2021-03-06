// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.openlcb.swing.tie;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class ConsumerTablePaneTest {

    @Test
    public void testCTor() {
        ConsumerTablePane t = new ConsumerTablePane();
        Assert.assertNotNull("exists",t);
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        JUnitUtil.resetProfileManager();
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }

    // private final static Logger log = LoggerFactory.getLogger(ConsumerTablePaneTest.class);

}
