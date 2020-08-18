// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.plaf.macosx;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Tests for the Jdk9Application class
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class Jdk9ApplicationTest  {

   @Test
   public void testCtor(){
      Assert.assertNotNull(new Jdk9Application());
   }

}
