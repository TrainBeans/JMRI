// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmrix.lenz.hornbyelite;

import jmri.jmrix.ConnectionTypeList;
import org.openide.util.lookup.ServiceProvider;

/**
 * Returns a list of valid lenz XpressNet Connection Types.
 *
 * @author Bob Jacobsen Copyright (C) 2010
 * @author Kevin Dickerson Copyright (C) 2010
 */
@ServiceProvider(service = ConnectionTypeList.class)
public class EliteConnectionTypeList implements jmri.jmrix.ConnectionTypeList {

    public static final String HORNBY = "Hornby"; // NOI18N

    @Override
    public String[] getAvailableProtocolClasses() {
        String[] masterList = new jmri.jmrix.lenz.LenzConnectionTypeList().getAvailableProtocolClasses();

        String[] tempList = new String[masterList.length + 1];
        tempList[0] = "jmri.jmrix.lenz.hornbyelite.ConnectionConfig";
        int x = 1;
        for (String s : masterList) {
            tempList[x] = s;
            x++;
        }
        return tempList;
    }

    @Override
    public String[] getManufacturers() {
        return new String[]{HORNBY};
    }

}
