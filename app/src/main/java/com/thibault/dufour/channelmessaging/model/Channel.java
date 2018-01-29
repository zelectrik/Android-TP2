package com.thibault.dufour.channelmessaging.model;

import java.util.HashMap;

/**
 * Created by dufourth on 22/01/2018.
 */
public class Channel {

    private String channelId;
    private String name;
    private int connectedUsers;

    public Channel(String _channelId, String _name, int _connectedUsers)
    {
        setChannelId(_channelId);
        setName(_name);
        setConnectedUsers(_connectedUsers);
    }

    public int getConnectedUsers() {
        return connectedUsers;
    }

    public void setConnectedUsers(int connectedUsers) {
        this.connectedUsers = connectedUsers;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
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
                "Nombre d'utilisateurs connectés : " + connectedUsers;
    }
}
