// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmris;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Base set of tests for decendents of the jmri.jmris.AbstractSignalMastServer class
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class AbstractSignalMastServerTest {

    protected AbstractSignalMastServer sms = null;

    @Test
    public void testCtor() {
        assertThat(sms).isNotNull();
    }

    @BeforeEach
    public void setUp(){
       jmri.util.JUnitUtil.setUp();
       sms = new AbstractSignalMastServer(){
          @Override
          public void sendStatus(String signalMast, String Status) {
          }
          @Override
          public void sendErrorStatus(String route) {
          }
          @Override
          public void parseStatus(String statusString) {
          }

       };
    }

    @AfterEach
    public void tearDown(){
       jmri.util.JUnitUtil.tearDown();
    }

}
