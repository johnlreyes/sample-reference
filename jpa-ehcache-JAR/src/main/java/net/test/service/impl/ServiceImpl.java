package net.test.service.impl;

import net.test.model.InfoDynamicModel;
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

    public static boolean DEBUG = true;

    public void addInfo(InfoModel info) {
        if (DEBUG) System.out.println("["+getClass().getName()+":addInfo] START");
        try {
            entityManager.persist(info);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (DEBUG) System.out.println("["+getClass().getName()+":addInfo] END");
    }

    public List<InfoModel> getAllInfo() {
        if (DEBUG) System.out.println("["+getClass().getName()+":getAllInfo] START");
        List<InfoModel> returnValue = new ArrayList<InfoModel>();
        try {
            Query query = entityManager.createQuery("FROM InfoModel im");
            returnValue.addAll((List<InfoModel>)query.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (DEBUG) System.out.println("["+getClass().getName()+":getAllInfo] END");
        return returnValue;
    }

    public void addStatus(StatusModel status) {
        if (DEBUG) System.out.println("["+getClass().getName()+":addStatus] START");
        try {
            entityManager.persist(status);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (DEBUG) System.out.println("["+getClass().getName()+":addStatus] END");
    }

    public StatusModel getStatus(String status) {
        if (DEBUG) System.out.println("["+getClass().getName()+":getStatus] START");
        StatusModel returnValue = null;
        try {
            Query query = entityManager.createQuery("FROM StatusModel sm WHERE sm.status=:status");
            query.setParameter("status", status);
            returnValue = (StatusModel)query.getSingleResult();
        } catch (NoResultException ex) {            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (DEBUG) System.out.println("["+getClass().getName()+":getStatus] END");
        return returnValue;
    }

    public InfoModel getInfo(int infoId) {
        InfoModel returnValue = null;
        if (DEBUG) System.out.println("["+getClass().getName()+":getInfo] START");
        try {
            Query query = entityManager.createQuery("FROM InfoModel im WHERE im.id=:infoId");
            query.setParameter("infoId", infoId);
            returnValue = (InfoModel) query.getSingleResult();
        } catch (NoResultException ex) {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (DEBUG) System.out.println("["+getClass().getName()+":getInfo] END");
        return returnValue;
    }

    public void addInfoDynamic(InfoDynamicModel infoDynamic) {
        if (DEBUG) System.out.println("["+getClass().getName()+":addInfoDynamic] START");
        try {
            entityManager.persist(infoDynamic);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (DEBUG) System.out.println("["+getClass().getName()+":addInfoDynamic] END");
    }

    public long getTotalInfo() {
        long returnValue = 0;
        if (DEBUG) System.out.println("["+getClass().getName()+":getTotalInfo] START");
        try {
            Query query = entityManager.createQuery("SELECT COUNT(*) FROM InfoModel");
            returnValue = (Long) query.getSingleResult();
        } catch (NoResultException ex) {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (DEBUG) System.out.println("["+getClass().getName()+":getTotalInfo] END");
        return returnValue;
    }
}
