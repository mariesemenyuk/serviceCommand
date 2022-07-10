package com.example.commandj11.service;

import com.example.commandj11.entity.GroupEntity;
import com.example.commandj11.entity.RoleEntity;
import com.example.commandj11.entity.UserEntity;
import com.example.commandj11.models.User;
import com.example.commandj11.repository.GroupRepository;
import com.example.commandj11.repository.RoleRepository;
import com.example.commandj11.repository.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandImplTest {

    @Mock
    private UserRepository userRepository;
    GroupRepository groupRepository = mock(GroupRepository.class);
    RoleRepository roleRepository = mock(RoleRepository.class);

    @InjectMocks
    CommandImpl commandImpl;

    static GroupEntity group;
    static RoleEntity role;

    @BeforeAll
    static void setUp() {
        group = new GroupEntity();
        group.setTitle("Blue");

        role = new RoleEntity("USER");
        role.setId(1);
    }

    @Test
    void saveUserIsOk() {

        UserEntity userEntity = new UserEntity();
        userEntity.setChatId("123456");
        userEntity.setFullName("Name");
        userEntity.setGroup(null);
        userEntity.setRole(role);

        User user = new User();
        user.setChatId("123456789");
        user.setFullName("Name");
        user.setGroup(null);

        when(roleRepository.find("USER")).thenReturn(role);
        when(userRepository.save(any(UserEntity.class), any(RoleEntity.class))).thenReturn(userEntity);

        String out = commandImpl.saveUser(user, "USER");

        assertEquals("Success", out);
        verify(userRepository).save(any(UserEntity.class), any(RoleEntity.class));
    }

    @Test
    void getGroups() {
        List<GroupEntity> groupsMock = new ArrayList<>();
        groupsMock.add(group);
        GroupEntity group2 = new GroupEntity();
        group2.setTitle("Green");
        groupsMock.add(group2);

        doReturn(groupsMock).when(groupRepository).findAll();

        Set<String> groupsTitles = commandImpl.getAllGroups();

        assertEquals(2, groupsTitles.size());
        assertTrue(groupsTitles.toString().contains("Green"));
        assertTrue(groupsTitles.toString().contains("Blue"));
    }

    @Test
    void getAllUsersAndGroups() {
        List<UserEntity> usersFromMock = new ArrayList<>();
        usersFromMock.add(new UserEntity("123456", "User Name", group, new RoleEntity("USER")));
        usersFromMock.add(new UserEntity("456789", "Teamlead Name", group, new RoleEntity("TEAMLEAD")));
        usersFromMock.add(new UserEntity("789456", "Teacher Name", null, new RoleEntity("TEACHER")));


        doReturn(usersFromMock).when(userRepository).findAll();

        Set<User> users = commandImpl.getAllUsersAndGroups();

        assertEquals(2, users.size());
        for (User user: users) {
            assertNotNull(user.getGroup());
        }
    }
}