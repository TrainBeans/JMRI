// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrit.display.layoutEditor.configurexml;

import java.awt.geom.Point2D;

import jmri.Turnout;
import jmri.configurexml.AbstractXmlAdapter;
import jmri.jmrit.display.layoutEditor.LayoutEditor;
import jmri.jmrit.display.layoutEditor.LayoutTurnout;
import jmri.jmrit.display.layoutEditor.TrackSegment;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This module handles configuration for display.LayoutTurnout objects for a
 * LayoutEditor.
 *
 * @author Bob Jacobsen Copyright (c) 2020
 * @author David Duchamp Copyright (c) 2007
 * @author George Warner Copyright (c) 2017-2019
 */
public class LayoutXOverXml extends LayoutTurnoutXml {
    
    public LayoutXOverXml() {
    }

    // private final static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LayoutXOverXml.class);
}
