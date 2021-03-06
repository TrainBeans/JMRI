// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.anyma;

import jmri.implementation.AbstractLightTestBase;
import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Tests for AnymaDMX_UsbLight class.
 *
 * @author George Warner Copyright (C) 2017
 * @since 4.9.6
 */
public class AnymaDMX_UsbLightTest extends AbstractLightTestBase {

    private AnymaDMX_SystemConnectionMemo _memo = null;

    @Override
    public void testCreate() {
        t = new AnymaDMX_UsbLight("DL1", "Test Light", _memo);
        Assert.assertNotNull("testCreate", t);
    }

    @Override
    public int numListeners() {
        return 0;
    }

    @Override
    public void checkOnMsgSent() {
    }

    @Override
    public void checkOffMsgSent() {
    }

    @BeforeEach
    @Override
    public void setUp() {
        JUnitUtil.setUp();
        jmri.util.JUnitUtil.initDefaultUserMessagePreferences();
        _memo = new AnymaDMX_SystemConnectionMemo();
        t = new AnymaDMX_UsbLight("DL1", "Test Light", _memo);
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }
}
