/*
 * The License for this software can be found in the file LICENSE that is included with the distribution.
 */
package org.epics.pvdata.copy.timestampPlugin;

import org.epics.pvdata.copy.PVCopy;
import org.epics.pvdata.copy.PVFilter;
import org.epics.pvdata.copy.PVPlugin;
import org.epics.pvdata.copy.PVPluginRegistry;
import org.epics.pvdata.property.PVTimeStamp;
import org.epics.pvdata.property.PVTimeStampFactory;
import org.epics.pvdata.pv.PVField;
/**
 * A Plugin for a filter that sets a timeStamp to the current time.
 * @author mrk
 * @since 2017.02.23
 */
public class TimestampPlugin implements PVPlugin
{
	static String name = "timestamp";
	
	/**
	 * Constructor
	 */
	public TimestampPlugin()
	{
		PVPluginRegistry.registerPlugin(name,this);
	}
	
	/* (non-Javadoc)
	 * @see org.epics.pvdata.copy.PVPlugin#create(java.lang.String, org.epics.pvdata.copy.PVCopy, org.epics.pvdata.pv.PVField)
	 */
	public PVFilter create(String requestValue,PVCopy pvCopy,PVField master)
	{
		PVTimeStamp pvTimeStamp = PVTimeStampFactory.create();
		if(!pvTimeStamp.attach(master)) return null;
		boolean current = false;
		boolean copy = false;
		if(requestValue.equals("current")) current = true;
		if(requestValue.equals("copy")) copy = true;
	    return new TimestampFilter(current,copy,master);
	}

}
