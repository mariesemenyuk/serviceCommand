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
    public String saveUser(User user, String role) {
        UserEntity newUser = new UserEntity();
        newUser.setChatId(user.getChatId());
        newUser.setFullName(user.getFullName());
        if(user.getGroup() == null) newUser.setGroup(null);
        else {
            GroupEntity group = groupRepository.find(user.getGroup());
            newUser.setGroup(group);
        }
        RoleEntity userRole = roleRepository.find(role);

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
    public Set<String>  getAllGroups() {
        List<GroupEntity> allGroups = groupRepository.findAll();
        Set<String> groupsTitles = new HashSet<>();
        for (GroupEntity group: allGroups) {
            groupsTitles.add(group.getTitle());
        }
        return groupsTitles;
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

    @Override
    public User getUser(String chatId) {
        User user = new User();
        UserEntity userEntity = userRepository.find(chatId);
        user.setChatId(userEntity.getChatId());
        user.setFullName(userEntity.getFullName());
        if(userEntity.getGroup() == null) user.setGroup(null);
        else user.setGroup(userEntity.getGroup().getTitle());
        return user;
    }

    @Override
    public String getUserRole(String chatId) {
        UserEntity userEntity = userRepository.find(chatId);
        return userEntity.getRole().getTitle();
    }
}
