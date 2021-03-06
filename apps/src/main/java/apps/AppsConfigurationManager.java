// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package apps;

import apps.gui3.tabbedpreferences.EditConnectionPreferencesDialog;
import apps.gui3.tabbedpreferences.TabbedPreferencesAction;
import jmri.Application;
import jmri.implementation.JmriConfigurationManager;

import javax.swing.*;

public class AppsConfigurationManager extends JmriConfigurationManager {

    @Override
    protected boolean isEditDialogRestart() {
        return EditConnectionPreferencesDialog.showDialog();
    }
}
