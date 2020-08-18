// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.util.com.rbnb;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Tests for the UDPOutputStream class
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class UDPOutputStreamTest  {

   @Test
   public void testCtor(){
      Assert.assertNotNull(new UDPOutputStream());
   }

}
