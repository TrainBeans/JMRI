// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.util.zeroconf;

import java.util.HashMap;

import javax.jmdns.ServiceInfo;

import jmri.InstanceManager;
import jmri.util.JUnitUtil;
import jmri.web.server.WebServerPreferences;

import org.junit.jupiter.api.AfterAll;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.Assume;
import org.junit.jupiter.api.BeforeAll;

/**
 * Tests for the ZeroConfService class
 *
 * @author Paul Bender Copyright (C) 2014
 * @author Randall Wood Copyright (C) 2016
 */
public class ZeroConfServiceTest {

    private static final String HTTP = "_http._tcp.local.";

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
        JUnitUtil.setUp();
        JUnitUtil.resetProfileManager();
        JUnitUtil.initZeroConfServiceManager();
    }

    @AfterEach
    public void tearDown() throws Exception {
        JUnitUtil.resetZeroConfServiceManager();
        
        // wait for dns threads to end
        Thread.getAllStackTraces().keySet().forEach((t) -> 
            {
                String name = t.getName();
                if (! name.equals("dns.close in ZerConfServiceManager#stopAll")) return; // skip
                
                try {
                    t.join(5000); // wait up to 35 seconds for that thread to end; 
                } catch (InterruptedException e) {
                    // nothing, just means that thread was terminated externally
                }
            }
        );        
        
        JUnitUtil.tearDown();
    }

    /**
     * Test of create method, of class ZeroConfService.
     */
    @Test
    public void testCreate_String_int() {
        ZeroConfService result = ZeroConfService.create(HTTP, 9999);
        Assert.assertNotNull(result);
        Assert.assertEquals(InstanceManager.getDefault(WebServerPreferences.class).getRailroadName(), result.getName());
    }

    /**
     * Test of create method, of class ZeroConfService.
     */
    @Test
    public void testCreate_3args() {
        HashMap<String, String> properties = new HashMap<>();
        ZeroConfService result = ZeroConfService.create(HTTP, 9999, properties);
        Assert.assertNotNull(result);
        Assert.assertEquals(InstanceManager.getDefault(WebServerPreferences.class).getRailroadName(), result.getName());
    }

    /**
     * Test of create method, of class ZeroConfService.
     */
    @Test
    public void testCreate_6args() {
        String name = "my name"; // NOI18N
        HashMap<String, String> properties = new HashMap<>();
        ZeroConfService result = ZeroConfService.create(HTTP, name, 9999, 1, 1, properties);
        Assert.assertNotNull(result);
        Assert.assertEquals(name, result.getName());
    }

    /**
     * Test of getKey method, of class ZeroConfService.
     */
    @Test
    public void testGetKey() {
        String name = "my_name";
        ZeroConfService instance = ZeroConfService.create(HTTP, name, 9999, 0, 0, new HashMap<>());
        String result = instance.getKey();
        Assert.assertEquals(name + "." + HTTP, result);
    }

    /**
     * Test of getName method, of class ZeroConfService.
     */
    @Test
    public void testGetName() {
        ZeroConfService instance = ZeroConfService.create(HTTP, 9999);
        Assert.assertEquals(InstanceManager.getDefault(WebServerPreferences.class).getRailroadName(), instance.getName());
    }

    /**
     * Test of getType method, of class ZeroConfService.
     */
    @Test
    public void testGetType() {
        ZeroConfService instance = ZeroConfService.create(HTTP, 9999);
        Assert.assertEquals(HTTP, instance.getType());
    }

    /**
     * Test of getServiceInfo method, of class ZeroConfService.
     */
    @Test
    public void testServiceInfo() {
        ZeroConfService instance = ZeroConfService.create(HTTP, 9999);
        ServiceInfo result = instance.getServiceInfo();
        Assert.assertNotNull(result);
    }

    /**
     * Test of isPublished method, of class ZeroConfService.
     */
    @Test
    public void testIsPublished() {
        ZeroConfService instance = ZeroConfService.create(HTTP, 9999);
        Assert.assertFalse(instance.isPublished());
    }

    /**
     * Test of publish method, of class ZeroConfService.
     */
    @Test
    public void testPublish() {
        ZeroConfService instance = ZeroConfService.create(HTTP, 9999);
        Assert.assertFalse(instance.isPublished());
        // can fail if platform does not release earlier stopped service within 15 seconds
        instance.publish();
        Assume.assumeTrue("Timed out publishing ZeroConf Service", JUnitUtil.waitFor(() -> {
            return instance.isPublished() == true;
        }));
        Assert.assertTrue(instance.isPublished());
    }

    /**
     * Test of stop method, of class ZeroConfService.
     */
    @Test
    public void testStop() {
        ZeroConfService instance = ZeroConfService.create(HTTP, 9999);
        Assert.assertFalse(instance.isPublished());
        // can fail if platform does not release earlier stopped service within 15 seconds
        instance.publish();
        Assume.assumeTrue("Timed out publishing ZeroConf Service", JUnitUtil.waitFor(() -> {
            return instance.isPublished() == true;
        }));
        Assert.assertTrue(instance.isPublished());
        instance.stop();
        JUnitUtil.waitFor(() -> {
            return instance.isPublished() == false;
        }, "Stopping ZeroConf Service");
        Assert.assertFalse(instance.isPublished());
    }
}
