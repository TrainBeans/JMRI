// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.util;

import jmri.InstanceManager;
import jmri.Sensor;
import jmri.SensorManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;

/**
 *
 * @author Randall Wood Copyright 2018
 */
public class NamedBeanExpectedStateTest {

    public NamedBeanExpectedStateTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        JUnitUtil.initInternalSensorManager();
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }

    /**
     * Test of getExpectedState method, of class NamedBeanExpectedState.
     */
    @Test
    public void testGetExpectedState() {
        SensorManager sm = InstanceManager.getDefault(SensorManager.class);
        NamedBeanExpectedState<Sensor> instance = new NamedBeanExpectedState<>(sm.provideSensor("IS12"), 3);
        Assert.assertEquals(3, instance.getExpectedState().intValue()); // Assert needs both args to be int or Object
    }

    /**
     * Test of setExpectedState method, of class NamedBeanExpectedState.
     */
    @Test
    public void testSetExpectedState() {
        SensorManager sm = InstanceManager.getDefault(SensorManager.class);
        NamedBeanExpectedState<Sensor> instance = new NamedBeanExpectedState<>(sm.provideSensor("IS12"), 3);
        Assert.assertEquals(3, instance.getExpectedState().intValue());
        boolean thrown = false;
        try {
        instance.setExpectedState(null);
        Assert.assertNull(instance.getExpectedState());
        } catch (NullPointerException ex) {
            thrown = true;
        }
        Assert.assertTrue("NPE thrown setting null state", thrown);
        Assert.assertEquals(3, instance.getExpectedState().intValue());
        instance.setExpectedState(5);
        Assert.assertEquals(5, instance.getExpectedState().intValue());
    }

    /**
     * Test of getObject method, of class NamedBeanExpectedState.
     */
    @Test
    public void testGetObject() {
        SensorManager sm = InstanceManager.getDefault(SensorManager.class);
        NamedBeanExpectedState<Sensor> instance = new NamedBeanExpectedState<>(sm.provideSensor("IS12"), 3);
        Assert.assertEquals(sm.getSensor("IS12"), instance.getObject());
    }

    /**
     * Test of getName method, of class NamedBeanExpectedState.
     */
    @Test
    public void testGetName() {
        SensorManager sm = InstanceManager.getDefault(SensorManager.class);
        NamedBeanExpectedState<Sensor> instance = new NamedBeanExpectedState<>(sm.provideSensor("IS12"), 3);
        Assert.assertEquals(sm.getSensor("IS12").getDisplayName(), instance.getName());
    }

    /**
     * Test that NamedBeanExpectedState throws NPE if passed a null NamedBean
     */
    @Test
    public void testNullNamedBean() {
        // JUnit 5 Assert.throwsException() would be nice...
        SensorManager sm = InstanceManager.getDefault(SensorManager.class);
        boolean thrown = false;
        try {
            new NamedBeanExpectedState<>(null, 3);
        } catch (NullPointerException ex) {
            thrown = true;
        }
        Assert.assertTrue("NPE thrown for null bean", thrown);
        thrown = false;
        try {
            new NamedBeanExpectedState<>(sm.provideSensor("IS12"), null);
        } catch (NullPointerException ex) {
            thrown = true;
        }
        Assert.assertTrue("NPE thrown for null value", thrown);
    }
}
