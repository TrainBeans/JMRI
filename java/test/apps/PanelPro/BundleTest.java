// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package apps.PanelPro;

import org.junit.Assert;
import org.junit.jupiter.api.*;

/**
 * Tests for the Bundle class
 *
 * @author Bob Jacobsen Copyright (C) 2012
 */
public class BundleTest  {

    @Test public void testGoodKeysMessage() {
        Assert.assertEquals("File", Bundle.getMessage("MenuFile"));
        Assert.assertEquals("Turnout", Bundle.getMessage("BeanNameTurnout"));
    }

    @Test
    public void testBadKeyMessage() {
        Assert.assertThrows(java.util.MissingResourceException.class, () -> Bundle.getMessage("FFFFFTTTTTTT"));
    }

    @Test public void testGoodKeysMessageArg() {
        Assert.assertEquals("File", Bundle.getMessage("MenuFile", new Object[]{}));
        Assert.assertEquals("Turnout", Bundle.getMessage("BeanNameTurnout", new Object[]{}));
    }

    @Test
    public void testBadKeyMessageArg() {
        Assert.assertThrows(java.util.MissingResourceException.class, () -> Bundle.getMessage("FFFFFTTTTTTT", new Object[]{}));
    }

}
