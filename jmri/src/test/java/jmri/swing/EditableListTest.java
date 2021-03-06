// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.swing;

import org.junit.jupiter.api.Test;

/**
 * Tests for the jmri.swing.EditableList class.
 *
 * @author Bob Jacobsen Copyright 2014
 */
public class EditableListTest {

    @Test
    public void testCtor() {
        new EditableList<String>();
        new EditableList<Integer>();
        new EditableList<Object>();
    }

    @Test
    public void testCtorWithMode() {
        new EditableList<String>(new DefaultEditableListModel<String>());
        new EditableList<Integer>(new DefaultEditableListModel<Integer>());
        new EditableList<Object>(new DefaultEditableListModel<Object>());
    }

}
