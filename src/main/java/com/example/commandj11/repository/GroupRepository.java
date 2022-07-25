package com.example.commandj11.repository;

import com.example.commandj11.entity.GroupEntity;
import com.example.commandj11.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class GroupRepository {

    public GroupRepository() {
    }

    /**
     * Find group entity with title
     * @param title
     * @return
     */
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
                throw new RuntimeException("Group was not found. " + e.getMessage());
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return group;
    }

    /**
     * Save new group
     * @param groupEntity
     * @return
     */
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
                throw new RuntimeException("Group was not created. " + e.getMessage());
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return groupEntity;
    }

    /**
     * Return list of all existing groups
     * @return
     */
    public List<GroupEntity> findAll() {
        List<GroupEntity> groups = new ArrayList<>();

        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "FROM GroupEntity U";
            Query query = session.createQuery(hql, GroupEntity.class);
            groups = query.list();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw new RuntimeException("Groups were not found. " + e.getMessage());
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return groups;
    }

}
