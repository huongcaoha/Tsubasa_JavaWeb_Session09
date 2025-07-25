package com.ra.session09.repository;

import com.ra.session09.model.entity.Seed;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeedRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Seed> getAllAndPagination(int page, int size ,String searchName) {
        Session session = sessionFactory.getCurrentSession();
        int offset = (page > 0) ? (page - 1) * size : 0;
        String hql ;
        if (searchName != null && !searchName.isEmpty()) {
            hql = "from Seed s where s.seedName like :searchName";
            return session.createQuery(hql,Seed.class)
                    .setParameter("searchName", "%"+searchName+"%")
                    .setFirstResult(offset)
                    .setMaxResults(size)
                    .getResultList();
        }else {
            hql = "from Seed s";
            return session.createQuery(hql,Seed.class)
                    .setFirstResult(offset)
                    .setMaxResults(size)
                    .getResultList();
        }

    }

    public Long checkSeedNameExists(String seedName) {
       Session session = sessionFactory.getCurrentSession();
           return session.createQuery("select count(s) from Seed s where s.seedName = :seedName",Long.class)
                    .setParameter("seedName", seedName)
                    .uniqueResult();

    }

    public Long countAllSeeds(String searchName) {
        Session session = sessionFactory.getCurrentSession();
        String hql ;
        if (searchName != null && !searchName.isEmpty()) {
            hql = "select count(s) from Seed s where s.seedName like :searchName";
            return session.createQuery(hql,Long.class)
                    .setParameter("searchName", "%"+searchName+"%" )
                    .getSingleResult();
        }else {
            hql = "select count(s) from Seed s ";
            return session.createQuery(hql,Long.class)
                    .getSingleResult();
        }

    }

    public boolean addSeed(Seed seed){
       Session session = sessionFactory.getCurrentSession();
            session.save(seed);
            return true;
    }

    public boolean updateSeed(Seed seed){
        Session session = sessionFactory.getCurrentSession();
            session.merge(seed);
            return true;
    }

    public Seed findSeedById(Long id){
        Session session = sessionFactory.getCurrentSession();
          return session.get(Seed.class, id);
    }

    public boolean deleteSeedById(long id){
           Session session = sessionFactory.getCurrentSession();
                Seed seed = findSeedById(id);
                session.delete(seed);
                return true;
    }
}
