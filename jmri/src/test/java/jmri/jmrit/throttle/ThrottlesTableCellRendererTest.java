// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.throttle;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Test simple functioning of ThrottlesTableCellRenderer
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class ThrottlesTableCellRendererTest {

    @Test
    public void testCtor() {
        ThrottlesTableCellRenderer panel = new ThrottlesTableCellRenderer();
        Assert.assertNotNull("exists", panel);
    }

    @BeforeEach
    public void setUp() throws Exception {
        jmri.util.JUnitUtil.setUp();

    }

    @AfterEach
    public void tearDown() throws Exception {
        jmri.util.JUnitUtil.tearDown();

    }
}
