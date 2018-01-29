package com.thibault.dufour.channelmessaging.model;

import java.util.List;

/**
 * Created by dufourth on 29/01/2018.
 */
public class ChannelList {
    public List<Channel> getListeChannel() {
        return channels;
    }

    public void setListeChannel(List<Channel> channels) {
        this.channels = channels;
    }

    List<Channel> channels;

    public ChannelList(List<Channel> channels) {
        this.channels = channels;
    }
}
