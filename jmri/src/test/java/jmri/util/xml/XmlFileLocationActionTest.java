// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.util.xml;

import jmri.util.JUnitUtil;

import jmri.util.xml.XmlFileLocationAction;
import org.junit.jupiter.api.*;
import org.junit.Assert;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class XmlFileLocationActionTest {

    @Test
    public void testCTor() {
        XmlFileLocationAction t = new XmlFileLocationAction();
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

    // private final static Logger log = LoggerFactory.getLogger(XmlFileLocationActionTest.class);

}
