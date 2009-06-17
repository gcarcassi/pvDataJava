/**
 * Copyright - See the COPYRIGHT that is included with this disctibution.
 * EPICS JavaIOC is distributed subject to a Software License Agreement found
 * in file LICENSE that is included with this distribution.
 */
package org.epics.pvData.channelAccess;

import org.epics.pvData.pv.PVField;
import org.epics.pvData.pv.PVStructure;
import org.epics.pvData.pv.Requester;


/**
 * Interface for accessing a channel.
 * A channel is created via a call to ChannelAccess.createChannel(String channelName).
 * @author mrk
 *
 */
public interface Channel extends Requester{
    /**
     * Connect to data source.
     */
    void connect();
    /**
     * Disconnect from data source.
     */
    void disconnect();
    /**
     * Destroy the channel. It will not honor any further requests.
     */
    void destroy();
    /**
     * Get the channel name.
     * @return The name.
     */
    String getChannelName();
    /**
     * Get the channel listener.
     * @return The listener.
     */
    ChannelListener getChannelListener();
    /**
     * Is the channel connected?
     * @return (false,true) means (not, is) connected.
     */
    boolean isConnected();
    /**
     * Get a Structure which describes the subField.
     * GetStructureRequester.getDone is called after both client and server have processed the getStructure request.
     * This is for clients that want to introspect a PVRecord via channel access.
     * MAJEJ Neither client or server needs to save this info.
     * @param subField The name of the subField.
     * If this is null or an empty string the returned Structure is for the entire record.
     * @return The Structure.
     */
    void getStructure(String subField);
    /**
     * Create a PVStructure for communication with the server.
     * CreatePVStructureRequester.createDone is called after both client and server have processed the create request.
     * @param pvRequest A structure describing the desired set of fields from the remote PVRecord.
     * This has the same form as a pvRequest to PVCopyFactory.create.
     * @param structureName The name to give to the created PVStructure.
     * MATEJ Should it be required that this is unique for each call to getStructure?
     * @param shareData On the remote side should the companion PVStructure share data with the PVRecord. 
     */
    void createPVStructure(CreatePVStructureRequester rquester,PVStructure pvRequest,String structureName,boolean shareData);
    /**
     * Get the access rights for a field of a PVStructure created via a call to createPVStructure.
     * MATEJ Channel access can store this info via auxInfo.
     * @param pvField The field for which access rights is desired.
     * @return The access rights.
     */
    AccessRights getAccessRights(PVField pvField);
    /**
     * Create a ChannelProcess.
     * ChannelProcessRequester.channelProcessReady is called after both client and server are ready for
     * the client to make a process request.
     * @param channelProcessRequester The interface for notifying when this request is complete
     * and when channel completes processing.
     */
    void createChannelProcess(
        ChannelProcessRequester channelProcessRequester);
    /**
     * Create a ChannelGet.
     * ChannelGetRequester.channelGetReady is called after both client and server are ready for
     * the client to make a get request.
     * @param pvStructure A PVStructure created via a call to createPVStructure.
     * @param channelGetRequester The interface for notifying when this request is complete
     * and when a channel get completes.
     * @param process Process before getting data.
     */
    void createChannelGet(
            ChannelGetRequester channelGetRequester,PVStructure pvStructure,boolean process);
    /**
     * Create a ChannelPut.
     * ChannelPutRequester.channelPutReady is called after both client and server are ready for
     * the client to make a put request.
     * @param pvStructure A PVStructure created via a call to createPVStructure.
     * @param channelPutRequester The channelPutRequester.
     * @param process Should record be processed after put.
     */
    void createChannelPut(
        ChannelPutRequester channelPutRequester,PVStructure pvStructure,boolean process);
    /**
     * Create a ChannelPutGet.
     * ChannelPutGetRequester.channelPutGetReady is called after both client and server are ready for
     * the client to make a putGet request.
     * @param pvPutStructure A PVStructure created via a call to createPVStructure.
     * @param pvGetStructure A PVStructure created via a call to createPVStructure.
     * @param channelPutGetRequester The channelPutGetRequester.
     * @param process Process after put and before get.
     */
    void createChannelPutGet(
        ChannelPutGetRequester channelPutGetRequester,
        PVStructure pvPutStructure,PVStructure pvGetStructure,
        boolean process);
    /**
     * Create a ChannelMonitor.
     * ChannelMonitorRequester.channelMonitorReady is called after both client and server are ready for
     * the client to make a monitor request.
     * @param pvStructure A PVStructure created via a call to createPVStructure.
     * This can be null in which case a monitor event will be issues whenever any field in the PVRecord is modified.
     * @param channelMonitorRequester The channelMonitorRequester.
     */
    void createChannelMonitor(
        ChannelMonitorRequester channelMonitorRequester,PVStructure pvStructure);
}