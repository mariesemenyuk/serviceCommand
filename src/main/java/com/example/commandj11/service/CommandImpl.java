package com.example.commandj11.service;

import com.example.commandj11.entity.GroupEntity;
import com.example.commandj11.entity.RoleEntity;
import com.example.commandj11.entity.UserEntity;
import com.example.commandj11.models.User;
import com.example.commandj11.repository.GroupRepository;
import com.example.commandj11.repository.RoleRepository;
import com.example.commandj11.repository.UserRepository;
import jakarta.jws.WebService;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Save user and his role in DB. Group can be empty. In this case null for group is saved in DB.
     * @param user
     * @param role
     * @return
     */
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

    /**
     * Create group with specific name
     * @param name
     * @return
     */
    @Override
    public String createGroup(String name) {

        GroupEntity newGroup = new GroupEntity();
        newGroup.setTitle(name);
        groupRepository.save(newGroup);

        return "Success";
    }

    /**
     * Add user with specific chatId to group with groupName
     * @param chatId
     * @param groupName
     * @return
     */
    @Override
    public String addUserToGroup(String chatId, String groupName) {
        userRepository.updateUserGroup(chatId, groupName);
        return "Success";
    }

    /**
     * Removes user from the group
     * @param chatId
     * @return
     */
    @Override
    public String deleteUserFromGroup(String chatId) {
        userRepository.deleteUserFromGroup(chatId);
        return "Success";
    }

    /**
     * Return all existing groups
     * @return Set of group titles
     */
    @Override
    public ArrayList<String>  getAllGroups() {
        List<GroupEntity> allGroups = groupRepository.findAll();
        ArrayList<String> groupsTitles = new ArrayList<>();
        for (GroupEntity group: allGroups) {
            groupsTitles.add(group.getTitle());
        }
        return groupsTitles;
    }

    /**
     * Returns all users. Info about each user contains chatId, full name and group title
     * @return
     */
    @Override
    public ArrayList<User> getAllUsersAndGroups() {
        ArrayList<User> userSet = new ArrayList<>();
        List<UserEntity> allUsers = userRepository.findAll();
        for (UserEntity user: allUsers) {
            if(user.getRole().getTitle().equals("USER") || user.getRole().getTitle().equals("TEAMLEAD")) {
                User newUser = new User();
                newUser.setChatId(user.getChatId());
                newUser.setFullName(user.getFullName());
                if (user.getGroup() == null) newUser.setGroup(null);
                else newUser.setGroup(user.getGroup().getTitle());
                userSet.add(newUser);
            }
        }
        return userSet;
    }

    /**
     * Get a list of all users' chatId
     * @return
     */
    @Override
    public ArrayList<String> getAllChatIds() {
        List<UserEntity> allUsers = userRepository.findAll();
        ArrayList<String> chatIds = new ArrayList<>();
        for (UserEntity user: allUsers) {
            chatIds.add(user.getChatId());
        }
        return chatIds;
    }

    /**
     * Get info(chatId, full name, group title) about one specific user
     * @param chatId
     * @return
     */
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

    /**
     * Get role(USER, TEACHER, TEAMLEAD, ADMIN) for specific user
     * @param chatId
     * @return
     */
    @Override
    public String getUserRole(String chatId) {
        UserEntity userEntity = userRepository.find(chatId);
        return userEntity.getRole().getTitle();
    }
}
