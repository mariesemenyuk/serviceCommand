package com.example.commandj11.models;

import com.example.commandj11.entity.GroupEntity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = { "chatId", "fullName", "group" })
public class User {
    private String chatId;
    private String fullName;
    private String group;

    public User() {
    }

    public User(String chatId, String fullName, String group) {
        this.chatId = chatId;
        this.fullName = fullName;
        this.group = group;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
