package com.example.commandj11.service;

import com.example.commandj11.entity.GroupEntity;
import com.example.commandj11.entity.RoleEntity;
import com.example.commandj11.entity.UserEntity;
import com.example.commandj11.models.User;
import com.example.commandj11.repository.GroupRepository;
import com.example.commandj11.repository.RoleRepository;
import com.example.commandj11.repository.UserRepository;
import jakarta.jws.WebService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebService(endpointInterface = "com.example.commandj11.service.Command")
public class CommandImpl implements Command{

    private static class SingletonHelper {
        private static final CommandImpl INSTANCE = new CommandImpl();
    }

    public static CommandImpl getInstance() {
        return CommandImpl.SingletonHelper.INSTANCE;
    }

    private RoleRepository roleRepository = new RoleRepository();

    private UserRepository userRepository = new UserRepository();

    private GroupRepository groupRepository = new GroupRepository();

    @Override
    public String saveUser(String chatId, String fullName) {
        UserEntity newUser = new UserEntity();
        newUser.setChatId(chatId);
        newUser.setFullName(fullName);
        RoleEntity userRole = roleRepository.find("USER");

        userRepository.save(newUser, userRole);
        return "Success";
    }

    @Override
    public String createGroup(String name) {

        GroupEntity newGroup = new GroupEntity();
        newGroup.setTitle(name);
        groupRepository.save(newGroup);

        return "Success";
    }

    @Override
    public String addUserToGroup(String chatId, String groupName) {
        userRepository.updateUserGroup(chatId, groupName);
        return "Success";
    }

    @Override
    public String deleteUserFromGroup(String chatId) {
        userRepository.deleteUserFromGroup(chatId);
        return "Success";
    }

    @Override
    public Set<User> getAllUsersAndGroups() {
        Set<User> userSet = new HashSet<>();
        List<UserEntity> allUsers = userRepository.findAll();
        for (UserEntity user: allUsers) {
            User newUser = new User();
            newUser.setChatId(user.getChatId());
            newUser.setFullName(user.getFullName());
            if(user.getGroup() == null) newUser.setGroup(null);
            else newUser.setGroup(user.getGroup().getTitle());
            userSet.add(newUser);
        }
        return userSet;
    }

    @Override
    public Set<String> getAllChatIds() {
        List<UserEntity> allUsers = userRepository.findAll();
        Set<String> chatIds = new HashSet<>();
        for (UserEntity user: allUsers) {
            chatIds.add(user.getChatId());
        }
        return chatIds;
    }

}
