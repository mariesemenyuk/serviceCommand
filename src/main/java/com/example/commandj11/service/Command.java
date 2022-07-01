package com.example.commandj11.service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Command {

    @WebMethod
    public String createGroup(String name);

    @WebMethod
    public String addUserToGroup(String username, String groupName);

    @WebMethod
    public String deleteUserFromGroup(String username);

    @WebMethod
    public String getAllUsersInGroup(String groupname);

    @WebMethod
    public String getAllUsers();

    @WebMethod
    public String getUser();

}
