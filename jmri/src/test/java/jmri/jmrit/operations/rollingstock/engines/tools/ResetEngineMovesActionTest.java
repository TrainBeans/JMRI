// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.operations.rollingstock.engines.tools;

import java.awt.GraphicsEnvironment;

import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.Assume;

import jmri.jmrit.operations.OperationsTestCase;

/**
 * @author Paul Bender Copyright (C) 2017
 */
public class ResetEngineMovesActionTest extends OperationsTestCase {

    @Test
    public void testCTor() {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        ResetEngineMovesAction t = new ResetEngineMovesAction();
        Assert.assertNotNull("exists", t);
    }

    // private final static Logger log =
    // LoggerFactory.getLogger(ResetEngineMovesActionTest.class);

}
