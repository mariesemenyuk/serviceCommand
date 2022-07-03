package com.example.commandj11.repository;

import com.example.commandj11.entity.GroupEntity;
import com.example.commandj11.entity.UserEntity;
import com.example.commandj11.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class GroupRepository {
    public GroupRepository() {
    }

    public GroupEntity find(String title) {
        GroupEntity group = new GroupEntity();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM GroupEntity G WHERE G.title = :title";
            Query query = session.createQuery(hql, GroupEntity.class);
            query.setParameter("title", title);
            group = (GroupEntity) query.list().get(0);
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
        return group;
    }

    public GroupEntity save(GroupEntity groupEntity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(groupEntity);
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
        return groupEntity;
    }

}
