package com.phone.validator.dal.hibernate;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.util.TriConsumer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.phone.validator.dal.dao.GenericDAO;
import com.phone.validator.dal.entity.Entity;

@Repository
public abstract class HibernateDAO<T extends Entity> implements GenericDAO<T> {

    private EntityManager entityManager;
    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    @Autowired
    public HibernateDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Query<T> createQuery(Session session, TriConsumer<CriteriaBuilder, Root<T>, CriteriaQuery<T>> caller) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> root = query.from(entityClass);
        caller.accept(builder, root, query);
        return session.createQuery(query);
    }

    protected T createQueryWithSingleResult(TriConsumer<CriteriaBuilder, Root<T>, CriteriaQuery<T>> caller) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<T> query = createQuery(currSession, caller);
        return query.getSingleResult();
    }

    protected List<T> createQueryWithListResult(TriConsumer<CriteriaBuilder, Root<T>, CriteriaQuery<T>> caller) {
        Session currSession = entityManager.unwrap(Session.class);
        Query<T> query = createQuery(currSession, caller);
        return query.getResultList();
    }
    
    @Override
    public List<T> findAll() {
        return createQueryWithListResult(
                (builder, root, query) -> query.select(root));
    }      
}
