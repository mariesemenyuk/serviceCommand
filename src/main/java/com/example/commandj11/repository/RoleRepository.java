package com.example.commandj11.repository;

import com.example.commandj11.entity.RoleEntity;
import com.example.commandj11.entity.UserEntity;
import com.example.commandj11.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RoleRepository {
    public RoleRepository() {
    }

    public RoleEntity find(String roleName) {
        RoleEntity role = new RoleEntity();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM RoleEntity U WHERE U.title = :title";
            Query query = session.createQuery(hql, RoleEntity.class);
            query.setParameter("title", roleName);
            role = (RoleEntity) query.list().get(0);
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
        return role;
    }

    public RoleEntity save(RoleEntity roleEntity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(roleEntity);
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
        return roleEntity;
    }
}
