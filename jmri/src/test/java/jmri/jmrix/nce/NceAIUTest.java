// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.nce;

import jmri.Sensor;

import org.junit.jupiter.api.*;
import org.junit.Assert;

/**
 * JUnit tests for the NceAIU class.
 *
 * @author Bob Jacobsen
 */
public class NceAIUTest {

    @Test
    public void testMarkChanges() {
        NceAIU a = new NceAIU();
        NceSensor s1 = new NceSensor("1");
        NceSensor s2 = new NceSensor("2");
        NceSensor s3 = new NceSensor("3");
        a.registerSensor(s1, 0);
        a.registerSensor(s2, 1);
        a.registerSensor(s3, 2);
        a.markChanges(2);
        Assert.assertEquals("check s1", Sensor.ACTIVE, s1.getKnownState());
        Assert.assertEquals("check s2", Sensor.INACTIVE, s2.getKnownState());
        Assert.assertEquals("check s3", Sensor.ACTIVE, s3.getKnownState());
    }

    @BeforeEach
    public void setUp() {
        jmri.util.JUnitUtil.setUp();
    }

    @AfterEach
    public void tearDown() {
        jmri.util.JUnitUtil.tearDown();
    }

}
