// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.util;

import java.util.List;
import java.io.File;
import java.io.IOException;
import jmri.InstanceManager;
import jmri.decoderdefn.DecoderFile;
import jmri.decoderdefn.DecoderIndexFile;
import jmri.roster.Roster;
import jmri.roster.RosterConfigManager;
import jmri.roster.RosterEntry;
import jmri.symbolicprog.CvTableModel;
import jmri.symbolicprog.VariableTableModel;
import jmri.profile.ProfileManager;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * Utility functions for build rosters and RosterEntries during tests.
 * </p>
 *
 * @author Bob Jacobsen Copyright (C) 2001, 2002, 2012
 * @author Paul Bender Copyright (C) 2019
 */
public class RosterTestUtil {

    // originally copied from RosterTest
    /**
     * create a test roster with known contents.
     *
     * @param rosterDir      location of the roster.
     * @param rosterFileName name of the roster file.
     * @return a roster with entries.
     * @throws java.io.IOException if unable to create a test roster
     */
    public static Roster createTestRoster(File rosterDir, String rosterFileName) throws IOException {
        FileUtil.createDirectory(rosterDir);

        // create a roster with known contents
        Roster r = new Roster(rosterDir.getAbsolutePath() + File.separator + rosterFileName);
        r.setRosterLocation(rosterDir.getAbsolutePath());
        r.setRosterIndexFileName(rosterFileName);
        addSP123ToRoster(r);
        addATSF123ToRoster(r);
        addUP123ToRoster(r);
        InstanceManager.getDefault(RosterConfigManager.class).setRoster(ProfileManager.getDefault().getActiveProfile(), r);
        return r;
    }

    private static void addATSF123ToRoster(Roster r) {
        CvTableModel cvTable;
        VariableTableModel varTable;
        RosterEntry e2 = new RosterEntry("ATSF123");
        e2.setId("ATSF123");
        e2.setDccAddress("456");
        e2.setLongAddress(true);
        e2.setRoadNumber("123");
        e2.setRoadName("ATSF");
        e2.setDecoderModel("Silver");
        e2.setDecoderFamily("Lenz Silver with 6th gen BEMF");
        e2.ensureFilenameExists();
        e2.putAttribute("key a", "value a");
        cvTable = new CvTableModel(null, null);
        varTable = new VariableTableModel(null, new String[]{"Name", "Value"}, cvTable);
        loadDecoderFromLoco(e2, varTable);
        e2.writeFile(cvTable, varTable);
        r.addEntry(e2);
    }

    private static void addUP123ToRoster(Roster r) {
        CvTableModel cvTable;
        VariableTableModel varTable;
        RosterEntry e3 = new RosterEntry("UP123");
        e3.setId("UP123");
        e3.setRoadNumber("123");
        e3.setRoadName("UP");
        e3.ensureFilenameExists();
        e3.putAttribute("key b", "value b");
        cvTable = new CvTableModel(null, null);
        varTable = new VariableTableModel(null, new String[]{"Name", "Value"}, cvTable);
        loadDecoderFromLoco(e3, varTable);
        e3.writeFile(cvTable, varTable);
        r.addEntry(e3);
    }

    private static void addSP123ToRoster(Roster r) {
        RosterEntry e1 = new RosterEntry("SP123");
        e1.setId("SP123");
        e1.setDccAddress("123");
        e1.setRoadNumber("123");
        e1.setRoadName("SP");
        e1.ensureFilenameExists();
        e1.putAttribute("key a", "value a");
        e1.putAttribute("key b", "value b");
        CvTableModel cvTable = new CvTableModel(null, null);
        VariableTableModel varTable = new VariableTableModel(null, new String[]{"Name", "Value"}, cvTable);
        loadDecoderFromLoco(e1, varTable);
        e1.writeFile(cvTable, varTable);
        r.addEntry(e1);
    }

    /**
     * Load the variable model for a particular roster entry into memory. This
     * was originally copied from PaneProgFrame
     *
     * @param r        the roster entry to load.
     * @param varTable the variable table to load.
     */
    public static void loadDecoderFromLoco(RosterEntry r, VariableTableModel varTable) {
        // get a DecoderFile from the locomotive xml
        String decoderModel = r.getDecoderModel();
        String decoderFamily = r.getDecoderFamily();
        log.debug("selected loco uses decoder {} {}", decoderFamily, decoderModel);
        // locate a decoder like that.
        List<DecoderFile> l = InstanceManager.getDefault(DecoderIndexFile.class).matchingDecoderList(null, decoderFamily, null, null, null, decoderModel);
        log.debug("found {} matches", l.size());
        if (l.isEmpty()) {
            log.debug("Loco uses {} {} decoder, but no such decoder defined", decoderFamily, decoderModel);
            // fall back to use just the decoder name, not family
            l = InstanceManager.getDefault(DecoderIndexFile.class).matchingDecoderList(null, null, null, null, null, decoderModel);
            log.debug("found {} matches without family key", l.size());
        }
        if (!l.isEmpty()) {
            DecoderFile d = l.get(0);
            loadDecoderFile(d, r, varTable);
        } else {
            if (decoderModel.equals("")) {
                log.debug("blank decoderModel requested, so nothing loaded");
            } else {
                log.warn("no matching \"{}\" decoder found for loco, no decoder info loaded", decoderModel);
            }
        }
    }

    // This was originally copied from PaneProgFrame
    private static void loadDecoderFile(DecoderFile df, RosterEntry re, VariableTableModel variableModel) {
        if (df == null) {
            log.warn("loadDecoder file invoked with null object");
            return;
        }
        log.debug("loadDecoderFile from {} {}", DecoderFile.fileLocation, df.getFileName());

        Element decoderRoot = null;

        try {
            decoderRoot = df.rootFromName(DecoderFile.fileLocation + df.getFileName());
        } catch (JDOMException | IOException e) {
            log.error("Exception while loading decoder XML file: {}", df.getFileName(), e);
        }
        df.getProductID();
        if (decoderRoot != null) {
            // load variables from decoder tree
            df.loadVariableModel(decoderRoot.getChild("decoder"), variableModel);

            // load function names
            re.loadFunctions(decoderRoot.getChild("decoder").getChild("family").getChild("functionlabels"));
        }
    }

    private final static Logger log = LoggerFactory.getLogger(RosterTestUtil.class);

}
