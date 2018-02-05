package com.thibault.dufour.channelmessaging.model;

import java.util.List;

/**
 * Created by dufourth on 05/02/2018.
 */
public class MessageList {
    List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public MessageList(List<Message> messages) {
        this.messages = messages;
    }



}
