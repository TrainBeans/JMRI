// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.throttle;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Test simple functioning of ThrottlesPreferencesPane
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class ThrottlesPreferencesPaneTest {

    @Test
    public void testCtor() {
        ThrottlesPreferencesPane panel = new ThrottlesPreferencesPane();
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
