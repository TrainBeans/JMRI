// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package apps.startup;

import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;
import org.junit.Assert;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class TriggerRouteModelTest {

    @Test
    public void testCTor() {
        TriggerRouteModel t = new TriggerRouteModel();
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

    // private final static Logger log = LoggerFactory.getLogger(TriggerRouteModelTest.class);

}
