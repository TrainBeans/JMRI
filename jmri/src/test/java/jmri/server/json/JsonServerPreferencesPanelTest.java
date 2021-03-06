// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.server.json;

import jmri.swing.PreferencesPanelTestBase;
import jmri.util.JUnitUtil;

import org.junit.Assert;
import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Paul Bender Copyright (C) 2017
 */
public class JsonServerPreferencesPanelTest extends PreferencesPanelTestBase<JsonServerPreferencesPanel> {

    @Override
    @BeforeEach
    public void setUp() {
        JUnitUtil.setUp();
        jmri.util.JUnitUtil.resetProfileManager();
        prefsPanel = new JsonServerPreferencesPanel();
    }

    // private final static Logger log = LoggerFactory.getLogger(JsonServerPreferencesPanelTest.class);

}
