/**
 * Copyright - See the COPYRIGHT that is included with this distribution.
 * EPICS pvData is distributed subject to a Software License Agreement found
 * in file LICENSE that is included with this distribution.
 */
package org.epics.pvData.monitor;

import org.epics.pvData.misc.BitSet;
import org.epics.pvData.pv.PVStructure;

/**
 * @author mrk
 *
 */
public interface MonitorElement {
    /**
     * Get the PVStructure.
     * @return The PVStructure.
     */
    PVStructure getPVStructure();
    /**
     * Get the bitSet showing which fields have changed.
     * @return The bitSet.
     */
    BitSet getChangedBitSet();
    /**
     * Get the bitSet showing which fields have been changed more than once.
     * @return The bitSet.
     */
    BitSet getOverrunBitSet();
}