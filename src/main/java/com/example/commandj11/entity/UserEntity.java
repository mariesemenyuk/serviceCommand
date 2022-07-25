package com.example.commandj11.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users", schema = "command")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "chat_id", unique = true)
    private String chatId;
    @Column(name = "full_name")
    private String fullName;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupEntity group;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    public UserEntity() {
    }

    public UserEntity(String chatId, String fullName, GroupEntity group) {
        this.chatId = chatId;
        this.fullName = fullName;
        this.group = group;
    }

    public UserEntity(String chatId, String fullName, GroupEntity group, RoleEntity role) {
        this.chatId = chatId;
        this.fullName = fullName;
        this.group = group;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public GroupEntity getGroup() {
        return group;
    }

    public void setGroup(GroupEntity group) {
        this.group = group;
    }
}
