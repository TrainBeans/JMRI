// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.bachrus;

import jmri.util.JUnitUtil;

import org.junit.jupiter.api.*;

/**
 * JUnit tests for the SpeedoPortController class.
 *
 * @author      Paul Bender Copyright (C) 2016
 */
public class SpeedoPortControllerTest extends jmri.jmrix.AbstractSerialPortControllerTestBase {

    @Override
    @BeforeEach
    public void setUp(){
       JUnitUtil.setUp();
       SpeedoSystemConnectionMemo memo = new SpeedoSystemConnectionMemo();
       apc = new SpeedoPortController(memo){
            @Override
            public boolean status(){
              return true;
            }
            @Override
            public void configure(){
            }
            @Override
            public java.io.DataInputStream getInputStream(){
                return null;
            }
            @Override
            public java.io.DataOutputStream getOutputStream(){
                return null;
            }

            @Override
            public String[] validBaudRates(){
               String[] retval = {"9600"};
               return retval;
            }

            /**
             * Open a specified port. The appName argument is to be provided to the
             * underlying OS during startup so that it can show on status displays, etc
             */
            @Override
            public String openPort(String portName, String appName){
               return "";
            }

       };
    }

    @Override
    @AfterEach
    public void tearDown(){
       JUnitUtil.tearDown();
    }

}
