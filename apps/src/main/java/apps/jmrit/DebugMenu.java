// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package apps.jmrit;

import jmri.util.xml.XmlFileValidateAction;

import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * Create a "Debug" menu containing the JMRI system-independent debugging tools.
 *
 * @author Bob Jacobsen Copyright 2003
 */
public class DebugMenu extends JMenu {

    public DebugMenu(String name, JPanel panel) {
        this(panel);
        setText(name);
    }

    public DebugMenu(JPanel panel) {

        super();

        setText(Bundle.getMessage("MenuDebug"));

        add(new jmri.jmrit.MemoryFrameAction(Bundle.getMessage("MenuItemMemoryUsageMonitor")));
        add(new JSeparator());
        add(new jmri.decoderdefn.InstallDecoderFileAction(Bundle.getMessage("MenuItemImportDecoderFile"), panel));
        add(new jmri.decoderdefn.InstallDecoderURLAction(Bundle.getMessage("MenuItemImportDecoderURL"), panel));
        add(new jmri.decoderdefn.DecoderIndexCreateAction(Bundle.getMessage("MenuItemRecreateDecoderIndex")));
        add(new jmri.roster.RecreateRosterAction(Bundle.getMessage("MenuItemRecreateRoster")));
        add(new jmri.roster.UpdateDecoderDefinitionAction(Bundle.getMessage("MenuItemUpdateDecoderDefinition")));
        add(new JSeparator());
        add(new XmlFileValidateAction(Bundle.getMessage("MenuItemValidateXMLFile"), panel));
        add(new jmri.decoderdefn.NameCheckAction(Bundle.getMessage("MenuItemCheckDecoderNames"), panel));
        add(new jmri.symbolicprog.tabbedframe.ProgCheckAction(Bundle.getMessage("MenuItemCheckProgrammerNames"), panel));
        add(new JSeparator());
        add(new jmri.jmrit.LogixLoadAction(Bundle.getMessage("MenuItemLogixDisabled"), panel));
        add(new apps.jmrit.log.LogAction(Bundle.getMessage("MenuItemLogAction")));
        add(new jmri.util.swing.JmriNamedPaneAction(Bundle.getMessage("MenuItemLogTreeAction"),
                new jmri.util.swing.sdi.JmriJFrameInterface(),
                "jmri.jmrit.log.Log4JTreePane"));
        add(new JSeparator());
        JMenu vsdMenu = new JMenu(Bundle.getMessage("MenuItemVSDecoder"));
        vsdMenu.add(new jmri.jmrit.vsdecoder.VSDecoderCreationAction(Bundle.getMessage("MenuItemVSDecoderManager")));
        vsdMenu.add(new jmri.jmrit.vsdecoder.swing.ManageLocationsAction(Bundle.getMessage("MenuItemVSDecoderLocationManager")));
        vsdMenu.add(new jmri.jmrit.vsdecoder.swing.VSDPreferencesAction(Bundle.getMessage("MenuItemVSDecoderPreferences")));
        add(vsdMenu);

    }
}
