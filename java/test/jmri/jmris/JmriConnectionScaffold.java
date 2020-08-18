// SPDX-License-Identifier: GPL-2.0 WITH Classpath-exception-2.0
package jmri.jmris;

import java.io.DataOutputStream;

/*
 * Test scaffold to be used when a JmriConnection object is required.
 *
 * @author Paul Bender Copyright (C) 2016
 */
public class JmriConnectionScaffold extends JmriConnection {

    private final StringBuilder output;

    public JmriConnectionScaffold(DataOutputStream s){
        super(s);
        output = new StringBuilder();
    }

    /**
     * Send a String to the instantiated connection.
     *
     * This method throws an IOException so the server or servlet holding the
     * connection open can respond to the exception if there is an immediate
     * failure. If there is an asynchronous failure, the connection is closed.
     *
     * @param message message to send
     */
    @Override
    public void sendMessage(String message) {
       // just append the message to a string builder, don't actually write it anywhere.
       output.append(message);
    }

    /*
     * get the contents of the string builder as a string
     * @return String contents of the string builder.
     */
    public String getOutput(){
        return output.toString();
    }

}
