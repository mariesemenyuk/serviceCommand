package com.example.commandj11.service;

import com.example.commandj11.models.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;


import java.util.List;
import java.util.Set;


@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Command {

    @WebMethod
    public String saveUser(String chatId, String fullName);

    @WebMethod
    public String createGroup(String name);

    @WebMethod
    public String addUserToGroup(String username, String groupName);

    @WebMethod
    public String deleteUserFromGroup(String username);

    @WebMethod
    public Set<User> getAllUsersAndGroups();

    @WebMethod
    public List<String> getAllChatIds();
}
