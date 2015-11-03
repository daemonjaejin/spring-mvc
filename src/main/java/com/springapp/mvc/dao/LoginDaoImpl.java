package com.springapp.mvc.dao;

import com.springapp.mvc.domain.UserVo;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by jin on 15. 10. 21..
 */
@Repository
public class LoginDaoImpl implements LoginDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserVo select() throws Exception{
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(UserVo.class);
        return (UserVo)criteria.list().get(0);
    }

    @Override
    public UserVo selectByUserId(String userId) throws Exception{
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(UserVo.class);
        criteria.add(Restrictions.eq("userId", userId));
        return (UserVo)criteria.list().get(0);
    }

    @Override
    public UserVo findByUserId(String userId) {
        Session session = getSessionFactory().getCurrentSession();
        Criteria criteria = session.createCriteria(UserVo.class);
        criteria.add(Restrictions.eq("userId", userId));
        return (UserVo)criteria.list().get(0);
    }

}
