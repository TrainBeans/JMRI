// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.can.cbus;

import jmri.jmrix.can.CanMessage;
import jmri.jmrix.can.CanReply;
import jmri.jmrix.can.CanSystemConnectionMemo;
import jmri.jmrix.can.TrafficControllerScaffold;
import jmri.jmrix.can.cbus.node.CbusNode;
import jmri.jmrix.can.cbus.node.CbusNodeTableDataModel;
import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 * @author Steve Young Copyright (C) 2019
 */
public class CbusMultiMeterTest extends jmri.implementation.AbstractMultiMeterTestBase {

    private CanSystemConnectionMemo memo;
    private TrafficControllerScaffold tcis;

    @BeforeEach
    @Override
    public void setUp() {
        JUnitUtil.setUp();
        
        memo = new CanSystemConnectionMemo();
        tcis = new TrafficControllerScaffold();
        memo.setTrafficController(tcis);
        mm = new CbusMultiMeter(memo);
    }
    
    @AfterEach
    @Override
    public void tearDown() {
        mm = null;
        tcis.terminateThreads();
        tcis=null;
        memo.dispose();
        memo = null;
        JUnitUtil.tearDown();
    }
    
    @Test
    public void testEnableDisable(){
        
        Assert.assertEquals("no listener to start",0,tcis.numListeners());
        
        mm.enable();
        Assert.assertEquals("listening",1,tcis.numListeners());
        mm.disable();
        Assert.assertEquals("not listening",0,tcis.numListeners());
        
        CbusNodeTableDataModel nodeModel = new CbusNodeTableDataModel(memo, 3,CbusNodeTableDataModel.MAX_COLUMN);
        jmri.InstanceManager.setDefault(CbusNodeTableDataModel.class,nodeModel );
        Assert.assertEquals("node table listening",1,tcis.numListeners());
        mm.enable();
        Assert.assertEquals("mm listening",2,tcis.numListeners());
        mm.disable();
        Assert.assertEquals("mm not listening",1,tcis.numListeners());
        
        CbusNode testCs = nodeModel.provideNodeByNodeNum(777);
        testCs.setCsNum(0);
        Assert.assertEquals("node + node table listening",2,tcis.numListeners());
        
        mm.enable();
        Assert.assertEquals("multimeter listening",3,tcis.numListeners());
        mm.disable();
        Assert.assertEquals("mm not listening",2,tcis.numListeners());
        
        nodeModel.dispose();
        testCs.dispose();
        
    }
    
    @Test
    public void testMultiMCanReply(){
        
        mm = new CbusMultiMeter(memo);
        
        CbusNodeTableDataModel nodeModel = new CbusNodeTableDataModel(memo, 3,CbusNodeTableDataModel.MAX_COLUMN);
        jmri.InstanceManager.setDefault(CbusNodeTableDataModel.class,nodeModel );
        
        CbusNode testCs = nodeModel.provideNodeByNodeNum(54321);
        testCs.setCsNum(0);
        
        mm.enable();
        
        CanReply r = new CanReply(tcis.getCanid());
        r.setNumDataElements(7);
        r.setElement(0, CbusConstants.CBUS_ACOF2);
        r.setElement(1, 0xd4); // nn 54322
        r.setElement(2, 0x32); // nn 54322
        r.setElement(3, 0x00); // en 1
        r.setElement(4, 0x01); // en 1
        r.setElement(5, 0x00); // 8mA
        r.setElement(6, 0x08); // 8mA
        ((CbusMultiMeter)mm).reply(r);
        
        Assert.assertEquals(0,mm.getCurrent(),0.001 ); // wrong opc
        
        r.setElement(0, CbusConstants.CBUS_ACON2);
        ((CbusMultiMeter)mm).reply(r);
        
        Assert.assertEquals(0,mm.getCurrent(),0.001 ); // wrong node
        
        r.setElement(2, 0x31); // nn 54321
        ((CbusMultiMeter)mm).reply(r);
        Assert.assertEquals(8,mm.getCurrent(),0.001 );
        
        r.setElement(5, 0x12); // 4807mA
        r.setElement(6, 0xc7); // 4807mA
        ((CbusMultiMeter)mm).reply(r);
        Assert.assertEquals(4807,mm.getCurrent(),0.001 );
        
        CanMessage m = new CanMessage(tcis.getCanid());
        m.setNumDataElements(7);
        m.setElement(0, CbusConstants.CBUS_ACON2);
        m.setElement(1, 0xd4); // nn 54321
        m.setElement(2, 0x31); // nn 54321
        m.setElement(3, 0x00); // en1
        m.setElement(4, 0x01); // en1
        m.setElement(5, 0x00); // 0mA
        m.setElement(6, 0x00); // 0mA
        
        ((CbusMultiMeter)mm).message(m);
        Assert.assertEquals(4807,mm.getCurrent(),0.001 ); // CanMessage Ignored
        
        r = new CanReply(tcis.getCanid());
        r.setNumDataElements(7);
        r.setElement(0, CbusConstants.CBUS_ACON2);
        r.setElement(1, 0xd4); // nn 54321
        r.setElement(2, 0x31); // nn 54321
        r.setElement(3, 0x00); // en1
        r.setElement(4, 0x01); // en1
        r.setElement(5, 0x00); // 0mA
        r.setElement(6, 0x00); // 0mA
        
        ((CbusMultiMeter)mm).reply(r);
        Assert.assertEquals(0,mm.getCurrent(),0.001 );
        
        // wrong event num
        r = new CanReply(tcis.getCanid());
        r.setNumDataElements(7);
        r.setElement(0, CbusConstants.CBUS_ACON2);
        r.setElement(1, 0xd4); // nn 54321
        r.setElement(2, 0x31); // nn 54321
        r.setElement(3, 0x00); // en3
        r.setElement(4, 0x03); // en3
        r.setElement(5, 0x12); // 4807mA
        r.setElement(6, 0xc7); // 4807mA
        
        ((CbusMultiMeter)mm).reply(r);
        Assert.assertEquals("Wrong event",0,mm.getCurrent(),0.001 );
        r.setElement(4, 0x01); // en1
        r.setRtr(true);
        
        ((CbusMultiMeter)mm).reply(r);
        Assert.assertEquals(0,mm.getCurrent(),0.001 );
        
        r.setExtended(true);
        r.setRtr(false);
        
        ((CbusMultiMeter)mm).reply(r);
        Assert.assertEquals(0,mm.getCurrent(),0.001 );
        
        r.setExtended(false);
        ((CbusMultiMeter)mm).reply(r);
        Assert.assertEquals(4807,mm.getCurrent(),0.001 );
        
        mm.disable();
        
        nodeModel.dispose();
        testCs.dispose();
        
    }
    
    @Test
    public void testMultiMVoltCanReply(){
        
        mm = new CbusMultiMeter(memo);
        
        CbusNodeTableDataModel nodeModel = new CbusNodeTableDataModel(memo, 3,CbusNodeTableDataModel.MAX_COLUMN);
        jmri.InstanceManager.setDefault(CbusNodeTableDataModel.class,nodeModel );
        
        CbusNode testCs = nodeModel.provideNodeByNodeNum(54321);
        testCs.setCsNum(0);
        
        mm.enable();
        
        CanReply r = new CanReply(tcis.getCanid());
        r.setNumDataElements(7);
        r.setElement(0, CbusConstants.CBUS_ACON2);
        r.setElement(1, 0xd4); // nn 54321
        r.setElement(2, 0x31); // nn 54321
        r.setElement(3, 0x00); // en 2
        r.setElement(4, 0x02); // en 2
        r.setElement(5, 0x00); // 12.9V
        r.setElement(6, 0x81); // 12.9V
        ((CbusMultiMeter)mm).reply(r);
        Assert.assertEquals(12.9,mm.getVoltage(),0.001 );
        
        r = new CanReply(tcis.getCanid());
        r.setNumDataElements(7);
        r.setElement(0, CbusConstants.CBUS_ACON2);
        r.setElement(1, 0xd4); // nn 54321
        r.setElement(2, 0x31); // nn 54321
        r.setElement(3, 0x00); // en 2
        r.setElement(4, 0x02); // en 2
        r.setElement(5, 0x01); // 25.6V
        r.setElement(6, 0x00); // 25.6V
        ((CbusMultiMeter)mm).reply(r);
        Assert.assertEquals(25.6,mm.getVoltage(),0.001 );
        
        r = new CanReply(tcis.getCanid());
        r.setNumDataElements(7);
        r.setElement(0, CbusConstants.CBUS_ACON2);
        r.setElement(1, 0xd4); // nn 54321
        r.setElement(2, 0x31); // nn 54321
        r.setElement(3, 0x00); // en2
        r.setElement(4, 0x01); // en2
        r.setElement(5, 0x00); // 0V
        r.setElement(6, 0x00); // 0V
        ((CbusMultiMeter)mm).reply(r);
        Assert.assertEquals(0,mm.getCurrent(),0.001 );
        
        mm.disable();
        
        nodeModel.dispose();
        testCs.dispose();
        
    }
    
//    @Test
//    @Override
//    public void testUpdateAndGetVoltage(){
//        Assert.assertEquals("no voltage", false, mm.hasVoltage() );
//    }
    
    @Test
    public void testSmallFuncs(){
        
        mm = new CbusMultiMeter(memo);
        
        ((CbusMultiMeter)mm).requestUpdateFromLayout();
        mm.initializeHardwareMeter();
        Assert.assertEquals("name", "CBUS", mm.getHardwareMeterName() );
        Assert.assertEquals("ma units", jmri.MultiMeter.CurrentUnits.CURRENT_UNITS_MILLIAMPS, mm.getCurrentUnits() );
        
        
    }

    // private final static Logger log = LoggerFactory.getLogger(CbusMultiMeterTest.class);

}
