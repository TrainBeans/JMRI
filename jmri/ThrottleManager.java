package jmri;

import jmri.jmrit.throttle.ThrottleFrame;
import java.util.Iterator;

/**
 * Interface for allocating and deallocating throttles.
 * <P>
 * "Address" is interpreted in the context of the DCC implementation.
 * Different systems will distrinquish between short and long addresses
 * in different ways.
 * @author			Glen Oberhauser
 * @version			$Revision: 1.9 $
 */
public interface ThrottleManager
{

    /**
     * Request a throttle, given a decoder address. When the decoder address
     * is located, the ThrottleListener gets a callback via the ThrottleListener.notifyThrottleFound
     * method.
     * @param address The decoder address desired.
     * @param l The ThrottleListener awaiting notification of a found throttle.
     */
    public void requestThrottle(int address, ThrottleListener l);


    /**
     * Cancel a request for a throttle
     * @param address The decoder address desired.
     * @param l The ThrottleListener cancelling request for a throttle.
     */
    public void cancelThrottleRequest(int address, ThrottleListener l);

}