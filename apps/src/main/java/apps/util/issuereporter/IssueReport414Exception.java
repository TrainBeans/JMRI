// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package apps.util.issuereporter;

/**
 * Exception that indicates an HTTP 414 error was received attempting to submit
 * issue.
 *
 * @author Randall Wood Copyright 2020
 */
public class IssueReport414Exception extends Exception {

    public IssueReport414Exception() {
        // nothing to do
    }

}
