package com.example.commandj11.repository;

import com.example.commandj11.entity.GroupEntity;
import com.example.commandj11.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


public class GroupRepository {
    private GroupRepository() {
    }

    private static class SingletonHelper {
        private static final GroupRepository INSTANCE = new GroupRepository();
    }

    public static GroupRepository getInstance() {
        return GroupRepository.SingletonHelper.INSTANCE;
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
}
