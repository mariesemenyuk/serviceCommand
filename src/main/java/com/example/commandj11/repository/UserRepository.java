package com.example.commandj11.repository;

import com.example.commandj11.entity.GroupEntity;
import com.example.commandj11.entity.UserEntity;
import com.example.commandj11.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;

public class UserRepository {

    private UserRepository() {
    }

    private static class SingletonHelper {
        private static final UserRepository INSTANCE = new UserRepository();
    }

    public static UserRepository getInstance() {
        return UserRepository.SingletonHelper.INSTANCE;
    }

    public UserEntity find(String username) {
        UserEntity user = new UserEntity();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM UserEntity U WHERE U.username = :username";
            Query query = session.createQuery(hql, GroupEntity.class);
            query.setParameter("username", username);
            user = (UserEntity) query.list().get(0);
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
        return user;
    }
}
