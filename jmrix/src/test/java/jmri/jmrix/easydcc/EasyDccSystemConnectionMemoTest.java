// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.easydcc;

import jmri.jmrix.SystemConnectionMemoTestBase;
import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;

/**
 * JUnit tests for the EasyDccSystemConnectionMemo class
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class EasyDccSystemConnectionMemoTest extends SystemConnectionMemoTestBase<EasyDccSystemConnectionMemo> {

    @Override
    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        scm = new EasyDccSystemConnectionMemo();
    }

    @Override
    @AfterEach
    public void tearDown() {
        scm.getTrafficController().terminateThreads();
        scm.dispose();
        JUnitUtil.clearShutDownManager(); // remove shutdown tasks left behind.
        JUnitUtil.tearDown();
    }

}
