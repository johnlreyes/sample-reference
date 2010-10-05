package net.test.service.impl;

import net.test.model.InfoModel;
import net.test.model.StatusModel;
import net.test.service.Service;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Transactional
public class ServiceImpl implements Service {

    @PersistenceContext
    private EntityManager entityManager;

    public void addInfo(InfoModel info) {
        System.out.println("["+getClass().getName()+":addInfo] START");
        try {
            entityManager.persist(info);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("["+getClass().getName()+":addInfo] END");
    }

    public List<InfoModel> getAllInfo() {
        System.out.println("["+getClass().getName()+":getAllInfo] START");
        List<InfoModel> returnValue = new ArrayList<InfoModel>();
        try {
            Query query = entityManager.createQuery("FROM InfoModel im");
            returnValue.addAll((List<InfoModel>)query.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("["+getClass().getName()+":getAllInfo] END");
        return returnValue;
    }

    public void addStatus(StatusModel status) {
        System.out.println("["+getClass().getName()+":addStatus] START");
        try {
            System.out.println("id="+status.getId());
            System.out.println("status="+status.getStatus());
            entityManager.persist(status);
            System.out.println("id="+status.getId());
            System.out.println("status="+status.getStatus());                           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("["+getClass().getName()+":addStatus] END");
    }

    public StatusModel getStatus(String status) {
        System.out.println("["+getClass().getName()+":getStatus] START");
        StatusModel returnValue = null;
        try {
            Query query = entityManager.createQuery("FROM StatusModel sm WHERE sm.status=:status");
            query.setParameter("status", status);
            returnValue = (StatusModel)query.getSingleResult();
        } catch (NoResultException ex) {            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("["+getClass().getName()+":getStatus] END");
        return returnValue;
    }
}
