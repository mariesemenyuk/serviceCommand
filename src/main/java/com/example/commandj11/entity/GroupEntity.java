package com.example.commandj11.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups", schema = "command")
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title", unique = true)
    private String title;

    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    private List<UserEntity> users;

    public GroupEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }
}
