// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package apps.startup;

import javax.swing.JFileChooser;

import jmri.util.JUnitUtil;
import jmri.util.startup.StartupModel;

import org.junit.jupiter.api.*;
import org.junit.Assert;

/**
 * PerformActionModelXmlTest.java
 * <p>
 * Test for the PerformActionModelXml class
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class AbstractFileModelFactoryTest {

    @Test
    @SuppressWarnings("deprecation")
    public void testCtor() {
        Assert.assertNotNull("PerformActionModelXml constructor", new AbstractFileModelFactory() {
            @Override
            protected JFileChooser setFileChooser() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public Class<? extends StartupModel> getModelClass() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public StartupModel newModel() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }

    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
    }

    @AfterEach
    public void tearDown() {
        JUnitUtil.tearDown();
    }

}
