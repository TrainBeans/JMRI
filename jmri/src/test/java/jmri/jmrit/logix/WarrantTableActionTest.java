// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.logix;

import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class WarrantTableActionTest {
    WarrantTableAction wta;

    @Test
    public void testCTor() {
        assertThat(wta).withFailMessage("exists").isNotNull();
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        wta = WarrantTableAction.getDefault();
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.clearShutDownManager(); // should be converted to check of scheduled ShutDownActions
        JUnitUtil.tearDown();
    }

    // private final static Logger log = LoggerFactory.getLogger(WarrantTableActionTest.class);

}
