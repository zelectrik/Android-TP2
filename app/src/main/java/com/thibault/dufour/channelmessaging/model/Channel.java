package com.thibault.dufour.channelmessaging.model;

import java.util.HashMap;

/**
 * Created by dufourth on 22/01/2018.
 */
public class Channel {

    private String channelID;
    private String name;
    private int connectedusers;

    public Channel(String _channelId, String _name, int _connectedUsers)
    {
        setChannelId(_channelId);
        setName(_name);
        setConnectedUsers(_connectedUsers);
    }

    public int getConnectedUsers() {
        return connectedusers;
    }

    public void setConnectedUsers(int connectedUsers) {
        this.connectedusers = connectedUsers;
    }

    public String getChannelId() {
        return channelID;
    }

    public void setChannelId(String channelId) {
        this.channelID = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + '\n' +
                "Nombre d'utilisateurs connectés : " + connectedusers;
    }
}
