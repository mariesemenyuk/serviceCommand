package com.example.commandj11.repository;

import com.example.commandj11.entity.GroupEntity;
import com.example.commandj11.entity.RoleEntity;
import com.example.commandj11.entity.UserEntity;
import com.example.commandj11.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public UserRepository() {
    }

    /**
     * Find user with chatId
     * @param chatId
     * @return
     */
    public UserEntity find(String chatId) {
        UserEntity user = new UserEntity();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM UserEntity U WHERE U.chatId = :chatId";
            Query query = session.createQuery(hql, UserEntity.class);
            query.setParameter("chatId", chatId);
            user = (UserEntity) query.list().get(0);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw new RuntimeException("User was not found.");
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    /**
     * Returns all existing users
     * @return
     */
    public List<UserEntity> findAll() {
        List<UserEntity> users = new ArrayList<>();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM UserEntity U";
            Query query = session.createQuery(hql, UserEntity.class);
            users = query.list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw new RuntimeException("Users were not found.");
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    /**
     * Save user entity to database
     * @param newUser
     * @param userRole
     * @return
     */
    public UserEntity save(UserEntity newUser, RoleEntity userRole) {
        Session session = null;
        Transaction transaction = null;
        UserEntity user = new UserEntity();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(newUser);
            session.flush();

            String hql = "FROM UserEntity U WHERE U.chatId = :chatId";
            Query query = session.createQuery(hql, UserEntity.class);
            query.setParameter("chatId", newUser.getChatId());
            user = (UserEntity) query.list().get(0);

            user.setRole(userRole);

            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw new RuntimeException("User was not saved.");
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

    /**
     * Set group to the user with chatId
     * @param chatId
     * @param groupName
     */
    public void updateUserGroup(String chatId, String groupName) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM GroupEntity G WHERE G.title = :title";
            Query query = session.createQuery(hql, GroupEntity.class);
            query.setParameter("title", groupName);
            GroupEntity group = (GroupEntity) query.list().get(0);

            hql = "FROM UserEntity U WHERE U.chatId = :chatId";
            query = session.createQuery(hql, UserEntity.class);
            query.setParameter("chatId", chatId);
            UserEntity user = (UserEntity) query.list().get(0);

            user.setGroup(group);

            session.update(user);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw new RuntimeException("User was not added to group.");
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Unset group for the user with chatId. Group field is null
     * @param chatId
     */
    public void deleteUserFromGroup(String chatId) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM UserEntity U WHERE U.chatId = :chatId";
            Query query = session.createQuery(hql, UserEntity.class);
            query.setParameter("chatId", chatId);
            UserEntity user = (UserEntity) query.list().get(0);

            user.setGroup(null);

            session.update(user);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw new RuntimeException("User was not deleted from group.");
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
