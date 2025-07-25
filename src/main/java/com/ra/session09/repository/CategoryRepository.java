package com.ra.session09.repository;

import com.ra.session09.model.entity.Category;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Category> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Category",Category.class).getResultList();
    }

    public Category findById(long id) {
        return sessionFactory.getCurrentSession().get(Category.class, id);
    }
}
