package com.example.commandj11.service;

import com.example.commandj11.models.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

import java.util.ArrayList;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Command {

    @WebMethod
    public String saveUser(User user, String role);

    @WebMethod
    public String createGroup(String title);

    @WebMethod
    public String addUserToGroup(String chatId, String groupName);

    @WebMethod
    public String deleteUserFromGroup(String chatId);

    @WebMethod
    public ArrayList<String> getAllGroups();

    @WebMethod
    public ArrayList<User> getAllUsersAndGroups();

    @WebMethod
    public ArrayList<String> getAllChatIds();

    @WebMethod
    public User getUser(String chatId);

    @WebMethod
    public String getUserRole(String chatId);
}
