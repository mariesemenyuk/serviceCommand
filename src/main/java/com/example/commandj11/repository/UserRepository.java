package com.example.commandj11.repository;

import com.example.commandj11.entity.GroupEntity;
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

    public UserEntity find(String charId) {
        UserEntity user = new UserEntity();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM UserEntity U WHERE U.charId = :charId";
            Query query = session.createQuery(hql, UserEntity.class);
            query.setParameter("charId", charId);
            user = (UserEntity) query.list().get(0);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

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
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return users;
    }

    public UserEntity save(UserEntity userEntity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(userEntity);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return userEntity;
    }

    public void updateUserGroup(String charId, String groupName) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM GroupEntity G WHERE G.title = :title";
            Query query = session.createQuery(hql, GroupEntity.class);
            query.setParameter("title", groupName);
            GroupEntity group = (GroupEntity) query.list().get(0);

            hql = "FROM UserEntity U WHERE U.charId = :charId";
            query = session.createQuery(hql, UserEntity.class);
            query.setParameter("charId", charId);
            UserEntity user = (UserEntity) query.list().get(0);

            user.setGroup(group);

            session.update(user);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void deleteUserFromGroup(String charId) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM UserEntity U WHERE U.charId = :charId";
            Query query = session.createQuery(hql, UserEntity.class);
            query.setParameter("charId", charId);
            UserEntity user = (UserEntity) query.list().get(0);

            user.setGroup(null);

            session.update(user);
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
