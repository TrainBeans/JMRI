// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.withrottle;

import jmri.InstanceManager;
import jmri.NamedBeanHandleManager;
import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Test simple functioning of DeviceServer
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class DeviceServerTest {

    @Test
    public void testCtor() {
        java.net.Socket s = new java.net.Socket();
        FacelessServer f = new FacelessServer(){
           @Override
           public void listen(){
           }
        };
        DeviceServer panel = new DeviceServer(s,f);
        jmri.util.JUnitAppender.assertErrorMessage("Stream creation failed (DeviceServer)");
        Assert.assertNotNull("exists", panel );
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        InstanceManager.setDefault(NamedBeanHandleManager.class, new NamedBeanHandleManager());
    }
    
    @AfterEach
    public void tearDown() {
        JUnitUtil.clearShutDownManager();
        JUnitUtil.tearDown();
    }
}
