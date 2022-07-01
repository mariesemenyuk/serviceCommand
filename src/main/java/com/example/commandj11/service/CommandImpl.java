package com.example.commandj11.service;

import com.example.commandj11.entity.GroupEntity;
import com.example.commandj11.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.jws.WebService;

@WebService(endpointInterface = "com.example.commandj11.service.Command")
public class CommandImpl implements Command{

    private static class SingletonHelper {
        private static final CommandImpl INSTANCE = new CommandImpl();
    }

    public static CommandImpl getInstance() {
        return CommandImpl.SingletonHelper.INSTANCE;
    }

    // TODO разобраться как возращать параметры в xml

    @Override
    public String createGroup(String name) {
        GroupEntity newGroup = new GroupEntity();
        newGroup.setTitle(name);
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            session.save(newGroup);
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

        return "Success";
    }

    @Override
    public String addUserToGroup(String username, String groupName) {


//        Session session = null;
//        Transaction transaction = null;
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
//
//            session.save(newGroup);
//            transaction.commit();
//        }
//        catch (Exception e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//        } finally {
//            if (session != null) {
//                session.close();
//            }
//        }
        return null;
    }

    @Override
    public String deleteUserFromGroup(String username) {
        return null;
    }

    @Override
    public String getAllUsersInGroup(String groupname) {
        return null;
    }

    @Override
    public String getAllUsers() {
        return null;
    }

    @Override
    public String getUser() {
        return null;
    }
}
